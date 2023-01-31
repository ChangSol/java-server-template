package org.changsol.api.apps.users.mappers;

import org.changsol.api.apps.users.dto.UserDto;
import org.changsol.api.apps.users.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    //Response Mapping
    UserDto.Response response(Users user);
}
