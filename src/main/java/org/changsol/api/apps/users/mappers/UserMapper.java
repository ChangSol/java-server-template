package org.changsol.api.apps.users.mappers;

import org.changsol.api.apps.samples.dtos.SampleMasterDto;
import org.changsol.api.apps.users.dtos.UserDto;
import org.changsol.api.apps.users.entitys.Users;
import org.changsol.api.utils.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<Users, UserDto.Response, UserDto.Response, SampleMasterDto.Response> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
