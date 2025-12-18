package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}