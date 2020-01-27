package com.pw.mini.mogger.audit.boundary.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class CreateNewAuditCommand {

    private Integer modelId;
    private Integer datasetId;
    private String measure;
    private Double value;
    private Timestamp timestamp;
}
