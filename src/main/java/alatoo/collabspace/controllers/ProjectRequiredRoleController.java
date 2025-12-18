package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.ProjectRequiredRoleDto;
import alatoo.collabspace.services.ProjectRequiredRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-required-roles")
@RequiredArgsConstructor
public class ProjectRequiredRoleController {
    private final ProjectRequiredRoleService service;

    @PostMapping
    public ResponseEntity<ProjectRequiredRoleDto> create(@RequestBody ProjectRequiredRoleDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectRequiredRoleDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectRequiredRoleDto>> listByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.listByProject(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectRequiredRoleDto> update(@PathVariable Long id, @RequestBody ProjectRequiredRoleDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}