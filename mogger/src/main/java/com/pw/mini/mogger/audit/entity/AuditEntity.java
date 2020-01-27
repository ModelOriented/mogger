package com.pw.mini.mogger.audit.entity;

import com.pw.mini.mogger.model.entity.ModelEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audits")
@IdClass(AuditId.class)
public class AuditEntity {

    @Id
    @Column(name = "model_id", nullable = false)
    private Integer modelId;

    @Id
    @Column(name = "dataset_id", nullable = false)
    private Integer datasetId;

    @Id
    @Column(name = "measure", nullable = false)
    private String measure;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false, insertable = false, updatable = false)
    private ModelEntity modelEntity;
}

@NoArgsConstructor
@AllArgsConstructor
class AuditId implements Serializable {

    private Integer modelId;
    private Integer datasetId;
    private String measure;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditId)) return false;
        AuditId auditId = (AuditId) o;
        return Objects.equals(modelId, auditId.modelId) &&
                Objects.equals(datasetId, auditId.datasetId) &&
                Objects.equals(measure, auditId.measure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, datasetId, measure);
    }
}

