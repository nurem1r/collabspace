package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.ProjectApplicationDto;
import alatoo.collabspace.entities.ProjectApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectApplicationMapper {
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "user.id", target = "userId")
    ProjectApplicationDto toDto(ProjectApplication entity);

    @Mapping(source = "projectId", target = "project.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "createdAt", ignore = true)
    ProjectApplication toEntity(ProjectApplicationDto dto);
}