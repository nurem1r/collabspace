package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.RegisterRequest;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.entities.enums.Role;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.HashSet;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "firstName", source = "name")
    User toUser(RegisterRequest request);

    @AfterMapping
    default void setUserRole(@MappingTarget User user) {
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        user.getRoles().add(Role.USER);
    }
}

