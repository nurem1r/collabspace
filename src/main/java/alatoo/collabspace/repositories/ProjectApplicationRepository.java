package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.ProjectApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectApplicationRepository extends JpaRepository<ProjectApplication, Long> {
}