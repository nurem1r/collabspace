package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
}