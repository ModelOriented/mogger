package com.pw.mini.mogger.plot.model_audit;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelAuditCommand {

    private Integer datasetId;
    private List<String> userNames;
    private String measure;
}
