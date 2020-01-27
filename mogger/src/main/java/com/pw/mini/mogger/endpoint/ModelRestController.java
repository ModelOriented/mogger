package com.pw.mini.mogger.endpoint;

import com.pw.mini.mogger.model.boundary.ModelService;
import com.pw.mini.mogger.model.boundary.command.CreateNewModelCommand;
import com.pw.mini.mogger.user.boundary.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mogger/api/v1/model")
public class ModelRestController {

    private final ModelService modelService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity createModel(@RequestBody CreateNewModelCommand command,
                                      @RequestHeader("userName") String userName,
                                      @RequestHeader("password") String password) {

        if (!userService.checkUser(userName, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        command.setTimestamp(timestamp);

        Integer modelId;

        try {
            modelId = modelService.createModel(command);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(modelId);
    }

    @GetMapping("/uniqueUsers/{taskName}")
    public List<String> findUniqueUsersByTaskName(@PathVariable String taskName) {
        return modelService.findUniqueUsersByTaskName(taskName);
    }
}