package com.pw.mini.mogger.audit.control.mapper;

import com.pw.mini.mogger.audit.boundary.vo.AuditVO;
import com.pw.mini.mogger.audit.entity.AuditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuditMapper {

    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

    AuditVO mapEntity(AuditEntity auditEntity);
}
