package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.ProjectMemberDto;
import alatoo.collabspace. services.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework. data.domain.Page;
import org.springframework.data.domain. Pageable;
import org. springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-members")
@RequiredArgsConstructor
public class ProjectMemberController {
    private final ProjectMemberService service;

    @PostMapping
    public ResponseEntity<ProjectMemberDto> add(@RequestBody ProjectMemberDto dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectMemberDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectMemberDto>> listByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.listByProject(projectId));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ProjectMemberDto>> listPaged(Pageable pageable) {
        return ResponseEntity.ok(service.listAllPaged(pageable));
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<ProjectMemberDto> rate(@PathVariable Long id, @RequestParam Integer rating, @RequestParam(required = false) String feedback) {
        return ResponseEntity.ok(service.rateMember(id, rating, feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}