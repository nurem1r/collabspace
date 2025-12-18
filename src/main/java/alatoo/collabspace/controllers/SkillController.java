package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.SkillDto;
import alatoo.collabspace.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillDto> create(@RequestBody SkillDto dto) {
        return ResponseEntity.ok(skillService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SkillDto>> list() {
        return ResponseEntity.ok(skillService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDto> update(@PathVariable Long id, @RequestBody SkillDto dto) {
        return ResponseEntity.ok(skillService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}