package com.pw.mini.mogger.endpoint;

import com.pw.mini.mogger.tag.boundary.TagService;
import com.pw.mini.mogger.tag.boundary.command.CreateNewTagCommand;
import com.pw.mini.mogger.tag.boundary.vo.TagVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mogger/api/v1/tag")
public class TagRestController {

    private final TagService tagService;

    @PostMapping()
    public ResponseEntity createTag(@RequestBody CreateNewTagCommand command) {
        try {
            tagService.createTag(command);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public List<TagVO> getTags() {
        return tagService.getAllTags();
    }
}
