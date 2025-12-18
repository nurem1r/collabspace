package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.UserDto;
import alatoo.collabspace.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(UserDto dto);
}