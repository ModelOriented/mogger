package com.pw.mini.mogger.model.entity;

import com.pw.mini.mogger.audit.entity.AuditEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "models")
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id", nullable = false)
    private Integer modelId;

    @Column(name = "dataset_id", nullable = false)
    private Integer datasetId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "model_description")
    private String modelDescription;

    @OneToMany(mappedBy = "modelEntity")
    List<AuditEntity> auditEntityList;
}
