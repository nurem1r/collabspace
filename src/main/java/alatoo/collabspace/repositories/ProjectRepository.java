package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query("SELECT COUNT(p) > 0 FROM Project p WHERE p.id = :projectId AND p.owner.email = :email")
  boolean existsByOwnerEmailAndId(@Param("email") String email,
      @Param("projectId") Long projectId);

  @Query("SELECT COUNT(pm) > 0 FROM ProjectMember pm " +
      "WHERE pm.project.id = :projectId AND pm.user.email = :email")
  boolean existsByProjectMemberEmailAndId(@Param("email") String email,
      @Param("projectId") Long projectId);

  @Query("SELECT CASE WHEN COUNT(pm) > 0 THEN true ELSE false END " +
      "FROM ProjectMember pm " +
      "JOIN pm.user u " +
      "WHERE pm.project.id = :projectId AND u.email = :email")
  boolean isUserProjectMember(@Param("email") String email,
      @Param("projectId") Long projectId);

}