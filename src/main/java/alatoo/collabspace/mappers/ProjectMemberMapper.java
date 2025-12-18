package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.ProjectMemberDto;
import alatoo.collabspace.entities.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "user.id", target = "userId")
    ProjectMemberDto toDto(ProjectMember entity);

    @Mapping(source = "projectId", target = "project.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "joinedAt", ignore = true)
    ProjectMember toEntity(ProjectMemberDto dto);
}