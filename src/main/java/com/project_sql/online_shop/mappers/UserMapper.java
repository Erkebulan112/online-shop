package com.project_sql.online_shop.mappers;

import com.project_sql.online_shop.dtos.UserDto;
import com.project_sql.online_shop.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
