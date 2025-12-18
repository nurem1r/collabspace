package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.ChatMemberDto;
import alatoo.collabspace. entities.ChatMember;
import org.mapstruct.Mapper;
import org.mapstruct. Mapping;

@Mapper(componentModel = "spring")
public interface ChatMemberMapper {
    @Mapping(source = "chat.id", target = "chatId")
    @Mapping(source = "user.id", target = "userId")
    ChatMemberDto toDto(ChatMember entity);

    @Mapping(source = "chatId", target = "chat. id")
    @Mapping(source = "userId", target = "user.id")
    ChatMember toEntity(ChatMemberDto dto);
}