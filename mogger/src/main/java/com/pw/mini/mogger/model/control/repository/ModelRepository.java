package com.pw.mini.mogger.model.control.repository;

import com.pw.mini.mogger.model.entity.ModelEntity;
import com.pw.mini.mogger.plot.model_audit.ModelAuditDataPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    ModelEntity findByModelId(Integer modelId);

    @Query("SELECT model.modelId as modelId, model.datasetId as modelsDatasetId, model.userName as userName," +
            " model.modelName as modelName, model.timestamp as modelsTimestamp, model.language as language," +
            " audit.datasetId as auditsDatasetId, audit.measure as measure, audit.value as value," +
            " audit.timestamp as auditsTimestamp " +
            "FROM AuditEntity audit LEFT JOIN audit.modelEntity model " +
            "WHERE audit.measure = :measure AND audit.datasetId = :datasetId AND model.userName = :userName")
    List<ModelAuditDataPoint> findModelAuditByDatasetIdAndUserNameAndMeasure(Integer datasetId, String userName, String measure);

    @Query("SELECT a.value FROM AuditEntity a INNER JOIN a.modelEntity m where m.modelId = :modelId")
    List<Double> findAuditsByModelId(Integer modelId);

    @Query("SELECT DISTINCT m.userName FROM ModelEntity m " +
            "LEFT JOIN DatasetEntity d on m.datasetId = d.datasetId " +
            "WHERE d.taskName = :taskName")
    List<String> findUniqueUsersByTaskName(String taskName);

}
