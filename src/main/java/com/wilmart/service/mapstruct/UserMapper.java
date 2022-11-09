package com.wilmart.service.mapstruct;

import com.wilmart.dto.UserDTO;
import com.wilmart.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO toDto(User entity);

    User toEntity(UserDTO dto);

    List<User> toEntity(List<UserDTO> dtos);

    List<UserDTO> toDto(List<User> entities);
}
