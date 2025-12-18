package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.ProjectDto;
import alatoo.collabspace.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "owner.id", target = "ownerId")
    ProjectDto toDto(Project project);

    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "requiredRoles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Project toEntity(ProjectDto dto);
}