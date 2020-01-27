package com.pw.mini.mogger.dataset.boundary.vo;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatasetVO {

    private String datasetName;
    private String userName;
    private String taskName;
    private Timestamp timestamp;
    private String datasetDescription;
    private String url;
}
