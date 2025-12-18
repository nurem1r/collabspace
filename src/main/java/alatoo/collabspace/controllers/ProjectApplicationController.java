package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.ProjectApplicationDto;
import alatoo.collabspace.services.ProjectApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-applications")
@RequiredArgsConstructor
public class ProjectApplicationController {
    private final ProjectApplicationService service;

    @PostMapping
    public ResponseEntity<ProjectApplicationDto> apply(@RequestBody ProjectApplicationDto dto) {
        return ResponseEntity.ok(service.apply(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectApplicationDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectApplicationDto>> listByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.listByProject(projectId));
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<ProjectApplicationDto> process(@PathVariable Long id, @RequestParam String action) {
        return ResponseEntity.ok(service.processApplication(id, action));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}