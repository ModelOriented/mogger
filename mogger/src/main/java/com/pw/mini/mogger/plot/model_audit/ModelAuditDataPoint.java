package com.pw.mini.mogger.plot.model_audit;

import com.pw.mini.mogger.model.entity.ModelEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.sql.Timestamp;

@Projection(name = "modelAuditDataPoint", types = { ModelEntity.class })
public interface ModelAuditDataPoint {

    Integer getModelId();

    @Value("#{target.modelsDatasetId}")
    Integer getModelsDatasetId();

    String getUserName();

    String getModelName();

    @Value("#{target.modelsTimestamp}")
    Timestamp getModelsTimestamp();

    String getLanguage();

    @Value("#{target.auditsDatasetId}")
    Integer getAuditsDatasetId();

    @Value("#{target.measure}")
    String getMeasure();

    @Value("#{target.value}")
    Double getValue();

    @Value("#{target.auditsTimestamp}")
    Timestamp getAuditsTimestamp();

    default String generateTooltipText() {
        return String.format("Model id: %d </br>Model dataset id: %d </br>" +
                             "Model language: %s </br>Audit dataset id: %d",
                getModelId(), getModelsDatasetId(),
                getLanguage(), getAuditsDatasetId());
    }

    default String generateTooltipTextX() {
        return String.format("Model id: %d </br>Model dataset id: %d </br>" +
                        "Model language: %s </br>AuditX dataset id: %d",
                getModelId(), getModelsDatasetId(),
                getLanguage(), getAuditsDatasetId());
    }

    default String generateTooltipTextTimestamp() {
        return String.format("Model id: %d </br>Model dataset id: %d </br>Model timestamp: %s </br>" +
                "Model language: %s </br>Audit dataset id: %d </br>Audit timestamp: %s",
                getModelId(), getModelsDatasetId(), getModelsTimestamp().toString(),
                getLanguage(), getAuditsDatasetId(), getAuditsTimestamp().toString());
    }

    default String generateTooltipTextTimestampX() {
        return String.format("Model id: %d </br>Model dataset id: %d </br>Model timestamp: %s </br>" +
                        "Model language: %s </br>AuditX dataset id: %d </br>AuditX timestamp: %s",
                getModelId(), getModelsDatasetId(), getModelsTimestamp().toString(),
                getLanguage(), getAuditsDatasetId(), getAuditsTimestamp().toString());
    }
}
