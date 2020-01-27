package com.pw.mini.mogger.endpoint;

import com.pw.mini.mogger.audit.boundary.AuditService;
import com.pw.mini.mogger.audit.boundary.command.CreateNewAuditCommand;
import com.pw.mini.mogger.model.boundary.ModelService;
import com.pw.mini.mogger.user.boundary.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mogger/api/v1/audit")
public class AuditRestController {

    private final AuditService auditService;
    private final UserService userService;
    private final ModelService modelService;

    @PostMapping()
    public ResponseEntity createAudit(@RequestBody CreateNewAuditCommand command,
                                      @RequestHeader("userName") String userName,
                                      @RequestHeader("password") String password) {

        if (!userService.checkUser(userName, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!modelService.checkUser(userName, command.getModelId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        command.setTimestamp(timestamp);

        try {
            auditService.createAudit(command);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/measures")
    public List<String> getMeasures() {
        return auditService.getAllAudits()
                .stream().map((item) -> item.getMeasure()).collect(Collectors.toList())
                .stream().distinct().collect(Collectors.toList());
    }

    /*@GetMapping("/{modelId}")
    public List<Double> findAuditsByModelId(@PathVariable Integer modelId) {
        return modelService.findAuditsByModelId(modelId);
    }*/

    @GetMapping("/uniqueMeasures/{taskName}")
    public List<String> findUniqueMeasuresByTaskName(@PathVariable String taskName) {
        return auditService.findUniqueMeasuresByTaskName(taskName);
    }
}
