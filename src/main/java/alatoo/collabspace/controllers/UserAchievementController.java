package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.UserAchievementDto;
import alatoo.collabspace.services.UserAchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-achievements")
@RequiredArgsConstructor
public class UserAchievementController {
    private final UserAchievementService service;

    @PostMapping
    public ResponseEntity<UserAchievementDto> assign(@RequestBody UserAchievementDto dto) {
        return ResponseEntity.ok(service.assign(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAchievementDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserAchievementDto>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}