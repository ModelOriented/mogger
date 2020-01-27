package com.pw.mini.mogger.user.control.mapper;

import com.pw.mini.mogger.user.boundary.vo.UserVO;
import com.pw.mini.mogger.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserVO mapEntity(UserEntity userEntity);
}
