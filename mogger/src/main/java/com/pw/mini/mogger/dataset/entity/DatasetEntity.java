package com.pw.mini.mogger.dataset.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "datasets")
public class DatasetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dataset_id", nullable = false)
    private Integer datasetId;

    @Column(name = "dataset_name", nullable = false)
    private String datasetName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Column(name = "dataset_description")
    private String datasetDescription;

    @Column(name = "url")
    private String url;
}
