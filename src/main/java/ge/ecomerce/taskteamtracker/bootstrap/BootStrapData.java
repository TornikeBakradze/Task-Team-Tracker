package ge.ecomerce.taskteamtracker.bootstrap;

import ge.ecomerce.taskteamtracker.domain.*;
import ge.ecomerce.taskteamtracker.repository.ProjectRepository;
import ge.ecomerce.taskteamtracker.repository.TaskRepository;
import ge.ecomerce.taskteamtracker.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    public void run(String... args) {
        loadProject();
        loadTask();
        loadTeamMember();
    }

    public void loadProject() {
        Project project1 = new Project();
        project1.setName("Project Alpha");
        project1.setDescription("This is the first project.");

        Project project2 = new Project();
        project2.setName("Project Beta");
        project2.setDescription("This is the second project.");

        Project project3 = new Project();
        project3.setName("Project Gamma");
        project3.setDescription("This is the third project.");

        projectRepository.save(project1);
        projectRepository.save(project2);
        projectRepository.save(project3);
    }

    public void loadTask() {
        Project project1 = projectRepository.findByName("Project Alpha")
                .orElseThrow(() -> new RuntimeException("Project Alpha not found"));
        Project project2 = projectRepository.findByName("Project Beta")
                .orElseThrow(() -> new RuntimeException("Project Beta not found"));
        Project project3 = projectRepository.findByName("Project Gamma")
                .orElseThrow(() -> new RuntimeException("Project Gamma not found"));

        Task task1 = Task.builder()
                .title("Task 1")
                .description("Alpha task one")
                .priority(Priority.HIGH)
                .status(TaskStatus.TODO)
                .dueDate(new Date(System.currentTimeMillis() + 86400000))
                .project(project1)
                .build();

        Task task2 = Task.builder()
                .title("Task 2")
                .description("Alpha task two")
                .priority(Priority.MEDIUM)
                .status(TaskStatus.IN_PROGRESS)
                .dueDate(new Date(System.currentTimeMillis() + 172800000))
                .project(project1)
                .build();

        Task task3 = Task.builder()
                .title("Task 3")
                .description("Beta task one")
                .priority(Priority.LOW)
                .status(TaskStatus.DONE)
                .dueDate(new Date(System.currentTimeMillis() - 86400000))
                .project(project2)
                .build();

        Task task4 = Task.builder()
                .title("Task 4")
                .description("Beta task two")
                .priority(Priority.HIGH)
                .status(TaskStatus.TODO)
                .dueDate(new Date(System.currentTimeMillis() + 604800000))
                .project(project2)
                .build();

        Task task5 = Task.builder()
                .title("Task 5")
                .description("Gamma task one")
                .priority(Priority.MEDIUM)
                .status(TaskStatus.IN_PROGRESS)
                .dueDate(new Date(System.currentTimeMillis() + 259200000))
                .project(project3)
                .build();

        Task task6 = Task.builder()
                .title("Task 6")
                .description("Gamma task two")
                .priority(Priority.LOW)
                .status(TaskStatus.TODO)
                .dueDate(new Date(System.currentTimeMillis() + 1209600000))
                .project(project3)
                .build();

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        taskRepository.save(task6);
    }

    public void loadTeamMember() {
        Task task1 = taskRepository.findByTitle("Task 1")
                .orElseThrow(() -> new RuntimeException("Task 1 not found"));
        Task task2 = taskRepository.findByTitle("Task 2")
                .orElseThrow(() -> new RuntimeException("Task 2 not found"));
        Task task3 = taskRepository.findByTitle("Task 3")
                .orElseThrow(() -> new RuntimeException("Task 3 not found"));
        Task task4 = taskRepository.findByTitle("Task 4")
                .orElseThrow(() -> new RuntimeException("Task 4 not found"));

        TeamMember member1 = TeamMember.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .tasks(new ArrayList<>(Arrays.asList(task1, task2)))
                .build();

        TeamMember member2 = TeamMember.builder()
                .name("Jane Smith")
                .email("jane.smith@example.com")
                .tasks(new ArrayList<>(Arrays.asList(task1, task3)))
                .build();

        TeamMember member3 = TeamMember.builder()
                .name("Alex Johnson")
                .email("alex.johnson@example.com")
                .tasks(new ArrayList<>(Arrays.asList(task2, task4)))
                .build();

        TeamMember member4 = TeamMember.builder()
                .name("Emily Brown")
                .email("emily.brown@example.com")
                .tasks(new ArrayList<>(Arrays.asList(task3, task4)))
                .build();

        teamMemberRepository.save(member1);
        teamMemberRepository.save(member2);
        teamMemberRepository.save(member3);
        teamMemberRepository.save(member4);
    }
}
