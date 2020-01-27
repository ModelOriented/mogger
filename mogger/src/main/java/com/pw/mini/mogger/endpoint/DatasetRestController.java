package com.pw.mini.mogger.endpoint;

import com.pw.mini.mogger.dataset.boundary.DatasetService;
import com.pw.mini.mogger.dataset.boundary.command.CreateNewDatasetCommand;
import com.pw.mini.mogger.user.boundary.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mogger/api/v1/dataset")
public class DatasetRestController {

    private final DatasetService datasetService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity createDataset(@RequestBody CreateNewDatasetCommand command,
                                        @RequestHeader("userName") String userName,
                                        @RequestHeader("password") String password) {

        if (!userService.checkUser(userName, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        command.setTimestamp(timestamp);

        Integer datasetId;

        try {
            datasetId = datasetService.createDataset(command);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(datasetId);
    }

    @GetMapping("/tasks")
    public List<String> getTasks() {
        return datasetService.getAllDatasets()
                .stream().map((item) -> item.getTaskName()).collect(Collectors.toList())
                .stream().distinct().collect(Collectors.toList());
    }

    @GetMapping("/uniqueDatasets/{taskName}")
    public String findDatasetsByTaskName(@PathVariable String taskName) {
        return datasetService.findDatasetsByTaskName(taskName);
    }
}
