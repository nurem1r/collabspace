package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.*;
import alatoo.collabspace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework. web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody CreateUserRequest req) {
        UserDto dto = UserDto.builder()
                .email(req.email())
                .firstName(req.firstName())
                .lastName(req.lastName())
                .faculty(req.faculty())
                .studyYear(req.studyYear())
                .bio(req.bio())
                .build();
        UserDto created = userService.create(dto, req.password());
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> list() {
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<UserDto>> listPaged(Pageable pageable) {
        return ResponseEntity.ok(userService.listAllPaged(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Управление навыками пользователя
    @PostMapping("/{id}/skills/{skillId}")
    public ResponseEntity<Set<SkillDto>> addSkill(@PathVariable Long id, @PathVariable Long skillId) {
        return ResponseEntity.ok(userService. addSkill(id, skillId));
    }

    @DeleteMapping("/{id}/skills/{skillId}")
    public ResponseEntity<Set<SkillDto>> removeSkill(@PathVariable Long id, @PathVariable Long skillId) {
        return ResponseEntity.ok(userService.removeSkill(id, skillId));
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<Set<SkillDto>> getSkills(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserSkills(id));
    }

    // Связанные данные
    @GetMapping("/{id}/achievements")
    public ResponseEntity<List<UserAchievementDto>> getAchievements(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserAchievements(id));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(@PathVariable Long id) {
        return ResponseEntity. ok(userService.getUserProjects(id));
    }

    @GetMapping("/{id}/memberships")
    public ResponseEntity<List<ProjectMemberDto>> getMemberships(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserMemberships(id));
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<List<ProjectApplicationDto>> getApplications(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserApplications(id));
    }

    @GetMapping("/{id}/chats")
    public ResponseEntity<List<ChatDto>> getChats(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserChats(id));
    }

    // Поиск
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> search(
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String faculty,
            @RequestParam(required = false) Integer studyYear) {
        return ResponseEntity.ok(userService.searchUsers(skill, faculty, studyYear));
    }

    public static record CreateUserRequest(
            String email,
            String password,
            String firstName,
            String lastName,
            String faculty,
            Integer studyYear,
            String bio
    ) {}
}