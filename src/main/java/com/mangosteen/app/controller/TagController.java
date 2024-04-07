package com.mangosteen.app.controller;

import java.util.Optional;

import com.mangosteen.app.annotation.CurrentUserId;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.exception.ResourceNotFoundException;
import com.mangosteen.app.manager.TagManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            @ApiResponse(responseCode = "400", description = "Tag input invalid"),
            @ApiResponse(responseCode = "500", description = "Tag created failed")
        })
    ResponseEntity<com.mangosteen.app.model.dao.Tag> createTag(@CurrentUserId Long id,
                                                               @RequestBody com.mangosteen.app.model.dao.Tag tag) {
        checkTag(id, tag);
        return ResponseEntity.ok(tagManager.createTag(tag, id));

    }

    @PatchMapping(produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update tag", description = "Return the updated tag information",
        responses = {
            @ApiResponse(responseCode = "200", description = "Tag created successfully"),
            @ApiResponse(responseCode = "400", description = "Tag input invalid"),
            @ApiResponse(responseCode = "404", description = "Tag id not found"),
            @ApiResponse(responseCode = "500", description = "Tag created failed")
        })
    ResponseEntity<com.mangosteen.app.model.dao.Tag> updateTag(@CurrentUserId Long userId,
                                                               @RequestBody
                                                               com.mangosteen.app.model.dao.Tag tagToUpdate) {
        checkTagNeedToUpdate(userId, tagToUpdate);
        return ResponseEntity.ok(tagManager.updateTag(tagToUpdate));

    }

    @GetMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Get tag", description = "Return the specific tag information",
        responses = {
            @ApiResponse(responseCode = "200", description = "Tag fetch successfully"),
            @ApiResponse(responseCode = "400", description = "Tag input invalid"),
            @ApiResponse(responseCode = "404", description = "Tag info not found"),
            @ApiResponse(responseCode = "500", description = "Tag fetch failed")
        })
    ResponseEntity<com.mangosteen.app.model.dao.Tag> getTagByTagId(@CurrentUserId Long userId,
                                                                   @PathVariable("id") Long tagId) {
        if (tagId == null || tagId < 0L) {
            throw new InvalidParameterException("The tag id must be not empty and positive. tag id: " + tagId);
        }

        val tag = Optional.ofNullable(tagManager.getTagByTagId(tagId))
                          .orElseThrow(() -> new ResourceNotFoundException(
                              "There is no related tag, tag id: "
                                  + tagId));
        return ResponseEntity.ok(tag);
    }


    private void checkTag(Long id, com.mangosteen.app.model.dao.Tag tag) {
        if (tag.getName().isEmpty()) {
            throw new InvalidParameterException("The tag name must be not empty");
        } else if (tagManager.checkTagExisted(tag.getName(), id)) {
            throw new InvalidParameterException("The tag name has already existed.");
        }
    }

    private void checkTagNeedToUpdate(Long userId, com.mangosteen.app.model.dao.Tag tagToUpdate) {
        if (tagToUpdate.getId() == null) {
            throw new InvalidParameterException("The tag id should be not empty.");
        }

        if (tagToUpdate.getName() == null
            && tagToUpdate.getIcon() == null
            && tagToUpdate.getDescription() == null) {
            throw new InvalidParameterException("There is no any information to update for tag.");
        }

        val tag = Optional.ofNullable(tagManager.getTagByTagId(tagToUpdate.getId()))
                          .orElseThrow(() -> new ResourceNotFoundException(
                              "There is no related tag to update, tag id: "
                                  + tagToUpdate.getId()));


        if (!tag.getUserId().equals(userId)) {
            throw new InvalidParameterException(
                String.format("The tag id %d and user id %d is not matched",
                              tag.getId(), userId));
        }
    }
}
