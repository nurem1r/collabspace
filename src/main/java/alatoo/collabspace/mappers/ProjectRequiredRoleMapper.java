package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.ProjectRequiredRoleDto;
import alatoo.collabspace.entities.ProjectRequiredRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectRequiredRoleMapper {
    @Mapping(source = "project.id", target = "projectId")
    ProjectRequiredRoleDto toDto(ProjectRequiredRole entity);

    @Mapping(source = "projectId", target = "project.id")
    ProjectRequiredRole toEntity(ProjectRequiredRoleDto dto);
}