package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}