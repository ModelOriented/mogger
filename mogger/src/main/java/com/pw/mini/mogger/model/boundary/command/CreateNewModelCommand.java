package com.pw.mini.mogger.model.boundary.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class CreateNewModelCommand {

    private Integer datasetId;
    private String userName;
    private String modelName;
    private String hash;
    private Timestamp timestamp;
    private String language;
    private String target;
    private String modelDescription;
    private List<String> tags;
}
