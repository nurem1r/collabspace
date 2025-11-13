package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.UserResponse;
import alatoo.collabspace.dto.UserSummaryDto;
import alatoo.collabspace.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {SkillMapper.class})
public interface UserMapper {

    UserResponse toResponse(User user);

    UserSummaryDto toSummary(User user);

    Set<UserSummaryDto> toSummaries(Set<User> users);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "university", source = "university")
    @Mapping(target = "course", source = "course")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "skills", source = "skills")
    UserResponse safeToResponse(User user);
}
