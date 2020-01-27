package com.pw.mini.mogger.model.control.mapper;

import com.pw.mini.mogger.model.boundary.vo.ModelVO;
import com.pw.mini.mogger.model.entity.ModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    ModelVO mapEntity(ModelEntity modelEntity);
}
