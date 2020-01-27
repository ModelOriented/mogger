package com.pw.mini.mogger.endpoint;

import com.pw.mini.mogger.model.boundary.ModelService;
import com.pw.mini.mogger.plot.model_audit.ModelAuditCommand;
import com.pw.mini.mogger.plot.model_audit.ModelAuditGenerator;
import com.pw.mini.mogger.plot.model_audit_double.ModelAuditDoubleCommand;
import com.pw.mini.mogger.plot.model_audit_double.ModelAuditDoubleGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mogger/api/v1/plot")
public class PlotRestController  {

    private final ModelService modelService;

    @PostMapping("/modelAudit")
    public ResponseEntity getModelAudit(@RequestBody ModelAuditCommand command) {

        ModelAuditGenerator generator = new ModelAuditGenerator(command, modelService);
        String data;

        try {
            data = generator.generateData();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/modelAuditDouble")
    public ResponseEntity getModelAuditDouble(@RequestBody ModelAuditDoubleCommand command) {

        ModelAuditDoubleGenerator generator = new ModelAuditDoubleGenerator(command, modelService);
        String data;

        try {
            data = generator.generateData();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/modelAuditTime")
    public ResponseEntity getModelAuditTime(@RequestBody ModelAuditCommand command) {

        ModelAuditGenerator generator = new ModelAuditGenerator(command, modelService);
        String data;

        try {
            data = generator.generateDataTime();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
