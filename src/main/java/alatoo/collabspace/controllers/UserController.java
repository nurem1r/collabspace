package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.UserDto;
import alatoo.collabspace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
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