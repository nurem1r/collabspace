package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.ProjectDto;
import alatoo. collabspace.dto.ProjectMemberDto;
import alatoo.collabspace.dto.ProjectApplicationDto;
import alatoo.collabspace.entities.enums.ProjectStatus;
import alatoo. collabspace.services.ProjectService;
import lombok. RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org. springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> create(@RequestBody ProjectDto dto) {
        return ResponseEntity.ok(projectService. create(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@projectSecurity.isProjectMember(#id) or hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> list() {
        return ResponseEntity.ok(projectService.listAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ProjectDto>> listPaged(Pageable pageable) {
        return ResponseEntity.ok(projectService.listAllPaged(pageable));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@projectSecurity.isProjectOwner(#id)")
    public ResponseEntity<ProjectDto> update(@PathVariable Long id, @RequestBody ProjectDto dto) {
        return ResponseEntity.ok(projectService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@projectSecurity.isProjectOwner(#id)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/complete")
    @PreAuthorize("@projectSecurity.isProjectOwner(#id)")
    public ResponseEntity<ProjectDto> complete(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.completeProject(id));
    }

    @GetMapping("/{id}/members")
    @PreAuthorize("@projectSecurity.isProjectOwner(#id)")
    public ResponseEntity<List<ProjectMemberDto>> getMembers(@PathVariable Long id) {
        return ResponseEntity.ok(projectService. getProjectMembers(id));
    }

    @GetMapping("/{id}/applications")
    @PreAuthorize("@projectSecurity.isProjectOwner(#id)")
    public ResponseEntity<List<ProjectApplicationDto>> getApplications(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectApplications(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectDto>> search(
            @RequestParam(required = false) ProjectStatus status,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(projectService.searchProjects(status, category, keyword));
    }
}