package alatoo.collabspace.services;

import alatoo.collabspace.entities.Skill;
import alatoo.collabspace.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    User createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(Long id, User updated);
    void deleteUser(Long id);

    List<User> findByNameContaining(String nameFragment);
    List<User> findByCourse(String course);

    User addSkillToUser(Long userId, Long skillId);
    User removeSkillFromUser(Long userId, Long skillId);
    Set<Skill> getUserSkills(Long userId);

    User addPoints(Long userId, int points);
    User removePoints(Long userId, int points);

    boolean existsByEmail(String email);
}
