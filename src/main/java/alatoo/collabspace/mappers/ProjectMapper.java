package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.ProjectRequest;
import alatoo.collabspace.dto.ProjectResponse;
import alatoo.collabspace.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {SkillMapper.class, UserMapper.class})
public interface ProjectMapper {

    @Mapping(target = "leaderId", source = "leader.id")
    @Mapping(target = "leaderName", source = "leader.name")
    @Mapping(target = "members", source = "members")
    @Mapping(target = "requiredSkills", source = "requiredSkills")
    @Mapping(target = "tags", expression = "java(tagsStringToSet(project.getTags()))")
    @Mapping(target = "status", source = "status")
    ProjectResponse toResponse(Project project);

    Set<ProjectResponse> toResponses(Set<Project> projects);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "leader", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "requiredSkills", ignore = true) // service will set required skills by ids
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "tags", expression = "java(tagsSetToString(request.getTags()))")
    Project toEntity(ProjectRequest request);

    default Set<String> tagsStringToSet(String tags) {
        if (tags == null || tags.trim().isEmpty()) return java.util.Collections.emptySet();
        return Arrays.stream(tags.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

    default String tagsSetToString(Set<String> tags) {
        if (tags == null || tags.isEmpty()) return null;
        return tags.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(","));
    }
}
