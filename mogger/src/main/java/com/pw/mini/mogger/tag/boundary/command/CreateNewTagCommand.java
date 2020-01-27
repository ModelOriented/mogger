package com.pw.mini.mogger.tag.boundary.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class CreateNewTagCommand {

    private Integer modelId;
    private String tagName;
}
