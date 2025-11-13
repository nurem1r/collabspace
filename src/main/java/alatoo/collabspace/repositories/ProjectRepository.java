package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.Project;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByLeader(User leader);

    @Query("SELECT p FROM Project p JOIN p.members m WHERE m.id = :userId")
    List<Project> findByMemberId(Long userId);

    List<Project> findByTagsContainingIgnoreCase(String tag);

    List<Project> findByStatus(Status status);
}
