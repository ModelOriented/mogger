package com.pw.mini.mogger.audit.boundary.vo;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditVO {

    private Integer modelId;
    private Integer datasetId;
    private String measure;
    private Double value;
    private Timestamp timestamp;
}
