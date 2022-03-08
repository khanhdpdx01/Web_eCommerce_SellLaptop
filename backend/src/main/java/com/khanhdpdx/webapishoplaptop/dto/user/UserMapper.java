package com.khanhdpdx.webapishoplaptop.dto.user;

import com.khanhdpdx.webapishoplaptop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDTO from(User user);
}
