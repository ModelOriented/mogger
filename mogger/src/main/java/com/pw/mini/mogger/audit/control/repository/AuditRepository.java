package com.pw.mini.mogger.audit.control.repository;

import com.pw.mini.mogger.audit.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditRepository extends JpaRepository<AuditEntity, Long> {

    Page<AuditEntity> findAll(Pageable pageable);

    @Query("SELECT DISTINCT a.measure FROM AuditEntity a " +
            "LEFT JOIN DatasetEntity d on a.datasetId = d.datasetId " +
            "WHERE d.taskName = :taskName")
    List<String> findUniqueMeasuresByTaskName(String taskName);
}
