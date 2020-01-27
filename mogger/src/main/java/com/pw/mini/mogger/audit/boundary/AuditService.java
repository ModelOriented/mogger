package com.pw.mini.mogger.audit.boundary;

import com.pw.mini.mogger.audit.boundary.command.CreateNewAuditCommand;
import com.pw.mini.mogger.audit.boundary.vo.AuditVO;
import com.pw.mini.mogger.audit.control.mapper.AuditMapper;
import com.pw.mini.mogger.audit.control.repository.AuditRepository;
import com.pw.mini.mogger.audit.entity.AuditEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditService {

    @PersistenceContext
    private EntityManager entityManager;
    private final AuditRepository auditRepository;

    @Transactional
    public void createAudit(CreateNewAuditCommand command) {
        AuditEntity auditEntity = AuditEntity.builder()
                .modelId(command.getModelId())
                .datasetId(command.getDatasetId())
                .measure(command.getMeasure())
                .value(command.getValue())
                .timestamp(command.getTimestamp())
                .build();
        entityManager.persist(auditEntity);
    }

    public List<AuditVO> getAllAudits(){
        return auditRepository.findAll()
                .stream()
                .map(e-> AuditMapper.INSTANCE.mapEntity(e))
                .collect(Collectors.toList());
    }

    public List<String> findUniqueMeasuresByTaskName(String taskName) {
        return auditRepository.findUniqueMeasuresByTaskName(taskName);
    }
}
