package com.pw.mini.mogger.endpoint;

import com.pw.mini.mogger.user.boundary.UserService;
import com.pw.mini.mogger.user.boundary.command.CreateNewUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mogger/api/v1/user")
public class UserRestController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity createNewUser(@RequestBody CreateNewUserCommand command) {
        try {
            userService.createUser(command);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
