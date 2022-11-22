package com.example.neighbor.infrastructure.mappers;

import com.example.neighbor.dto.UserAuthDTO;
import com.example.neighbor.dto.UserPublicDTO;
import com.example.neighbor.dto.UserRegisterDTO;
import com.example.neighbor.models.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public interface UserMapper {
    @Mapping(target="avatarId", source="user.avatar.id")
    UserPublicDTO userToUserPublicDto(User user);

    User userPublicDtoToUser(UserPublicDTO user);

    User userRegisterDtoToUser(UserRegisterDTO userRegisterDTO);

    UserAuthDTO userToUserAuthDto(User user);
}
