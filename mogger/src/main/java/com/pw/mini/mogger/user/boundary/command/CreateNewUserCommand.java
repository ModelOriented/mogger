package com.pw.mini.mogger.user.boundary.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class CreateNewUserCommand {

    private String userName;
    private String password;
}
