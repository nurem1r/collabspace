package alatoo.collabspace.controllers;

import alatoo. collabspace.dto.AchievementDto;
import alatoo.collabspace.entities.enums.AchievementSource;
import alatoo.collabspace.services.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework. data.domain.Page;
import org.springframework.data.domain. Pageable;
import org. springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService service;

    @PostMapping
    public ResponseEntity<AchievementDto> create(@RequestBody AchievementDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchievementDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<AchievementDto>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<AchievementDto>> listPaged(Pageable pageable) {
        return ResponseEntity. ok(service.listAllPaged(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AchievementDto> update(@PathVariable Long id, @RequestBody AchievementDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AchievementDto>> search(
            @RequestParam(required = false) AchievementSource source,
            @RequestParam(required = false) Long skillId) {
        return ResponseEntity.ok(service.searchAchievements(source, skillId));
    }
}