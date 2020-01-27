package com.pw.mini.mogger.tag.control.mapper;

import com.pw.mini.mogger.tag.boundary.vo.TagVO;
import com.pw.mini.mogger.tag.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagVO mapEntity(TagEntity tagEntity);
}
