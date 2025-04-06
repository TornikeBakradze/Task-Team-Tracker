package ge.ecomerce.taskteamtracker.repository;


import ge.ecomerce.taskteamtracker.domain.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}
