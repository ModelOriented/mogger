package com.pw.mini.mogger.plot.model_audit_double;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelAuditDoubleCommand {

    private Integer datasetIdX;
    private Integer datasetIdY;
    private List<String> userNames;
    private String measure;
}
