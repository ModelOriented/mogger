package com.pw.mini.mogger.dataset.control.mapper;

import com.pw.mini.mogger.dataset.boundary.vo.DatasetVO;
import com.pw.mini.mogger.dataset.entity.DatasetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DatasetMapper {

    DatasetMapper INSTANCE = Mappers.getMapper(DatasetMapper.class);

    DatasetVO mapEntity(DatasetEntity datasetEntity);
}
