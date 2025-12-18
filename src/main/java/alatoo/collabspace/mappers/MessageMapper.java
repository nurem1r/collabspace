package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.MessageDto;
import alatoo.collabspace.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "chat.id", target = "chatId")
    @Mapping(source = "sender.id", target = "senderId")
    MessageDto toDto(Message entity);

    @Mapping(source = "chatId", target = "chat.id")
    @Mapping(source = "senderId", target = "sender.id")
    @Mapping(target = "createdAt", ignore = true)
    Message toEntity(MessageDto dto);
}