package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.ChatDto;
import alatoo.collabspace.entities.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Mapping(source = "project.id", target = "projectId")
    ChatDto toDto(Chat entity);

    @Mapping(source = "projectId", target = "project.id")
    @Mapping(target = "createdAt", ignore = true)
    Chat toEntity(ChatDto dto);
}