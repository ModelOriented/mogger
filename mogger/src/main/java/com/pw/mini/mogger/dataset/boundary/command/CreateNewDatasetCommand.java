package com.pw.mini.mogger.dataset.boundary.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class CreateNewDatasetCommand {

    private String datasetName;
    private String userName;
    private String taskName;
    private Timestamp timestamp;
    private String datasetDescription;
    private String url;
}
