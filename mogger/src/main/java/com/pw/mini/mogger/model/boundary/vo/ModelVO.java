package com.pw.mini.mogger.model.boundary.vo;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelVO {

    private Integer datasetId;
    private String userName;
    private String modelName;
    private String hash;
    private Timestamp timestamp;
    private String language;
    private String target;
    private String modelDescription;
}
