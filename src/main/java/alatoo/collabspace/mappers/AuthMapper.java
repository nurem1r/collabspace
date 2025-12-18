package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.RegisterRequest;
import alatoo.collabspace.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "firstName", source = "name")
    User toUser(RegisterRequest request);

}
