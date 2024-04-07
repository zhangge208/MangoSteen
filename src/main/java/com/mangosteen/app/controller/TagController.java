package com.mangosteen.app.controller;

import com.mangosteen.app.annotation.CurrentUserId;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.manager.TagManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1.0/tags")
@Tag(name = "Tag APIs", description = "Related APIs for tag management")
public class TagController {

    private final TagManager tagManager;

    public TagController(TagManager tagManager) {
        this.tagManager = tagManager;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create tag", description = "Return the specific tag information",
        responses = {
            @ApiResponse(responseCode = "200", description = "Tag created successfully"),
            @ApiResponse(responseCode = "500", description = "Tag created failed")
        })

    ResponseEntity<com.mangosteen.app.model.dao.Tag> createTag(@CurrentUserId Long id,
                                                               @RequestBody com.mangosteen.app.model.dao.Tag tag) {
        checkTag(id, tag);
        return ResponseEntity.ok(tagManager.createTag(tag, id));

    }

    private void checkTag(Long id, com.mangosteen.app.model.dao.Tag tag) {
        if (tag.getName().isEmpty()) {
            throw new InvalidParameterException("The tag name must be not empty");
        } else if (tagManager.checkTagExisted(tag.getName(), id)) {
            throw new InvalidParameterException("The tag name has already existed.");
        }
    }
}
