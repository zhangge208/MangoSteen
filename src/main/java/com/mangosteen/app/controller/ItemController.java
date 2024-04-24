package com.mangosteen.app.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.github.pagehelper.PageInfo;
import com.mangosteen.app.annotation.CurrentUserId;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.manager.ItemManager;
import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemQueryParam;
import com.mangosteen.app.model.vo.ItemType;
import com.mangosteen.app.model.vo.ItemVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1.0/items")
public class ItemController {

    private final ItemManager itemManager;

    @Autowired
    public ItemController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ItemVO> createItem(@RequestBody ItemVO item,
                                             @CurrentUserId Long userId) {
        // validate
        itemManager.validateItem(item, userId);
        // create item
        return ResponseEntity.ok(itemManager.createNewItem(item, userId));
    }

    /**
     * Get item by item id API
     *
     * @param id     the item id
     * @param userId user id
     * @return item vo.
     */
    @GetMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ItemVO> getItemById(@PathVariable("id") Long id,
                                              @CurrentUserId Long userId) {
        if (id == null || id <= 0L) {
            throw new InvalidParameterException("The item id must be positive.");
        }
        val result = itemManager.getItemByItemId(id);

        if (!Objects.equals(result.getUserId(), userId)) {
            throw new InvalidParameterException("The user id and item id are mismatched.");
        }

        return ResponseEntity.ok(result);
    }

    @PatchMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update item", description = "Return the updated item information",
        responses = {
            @ApiResponse(responseCode = "200", description = "Item updated successfully"),
            @ApiResponse(responseCode = "400", description = "Item input invalid"),
            @ApiResponse(responseCode = "404", description = "Item id not found"),
            @ApiResponse(responseCode = "500", description = "Item updated failed")
        })
    public ResponseEntity<ItemVO> updateItem(@PathVariable("id") Long itemId,
                                             @RequestBody ItemVO itemVOForUpdating,
                                             @CurrentUserId Long userId) {
        // validation
        itemVOForUpdating.setId(itemId);
        itemVOForUpdating.setUserId(userId);
        itemManager.validateItemToUpdate(itemVOForUpdating);

        // update item &  return result
        return ResponseEntity.ok(itemManager.updateItem(itemVOForUpdating));

    }

    @GetMapping
    public ResponseEntity<PageInfo<Item>> queryItems(@RequestParam int pageNum,
                                                     @RequestParam int pageSize,
                                                     @CurrentUserId Long userId,
                                                     @RequestParam(required = false) ItemType type,
                                                     @RequestParam(required = false) String status,
                                                     @RequestParam(required = false) LocalDateTime startTime,
                                                     @RequestParam(required = false) LocalDateTime endTime,
                                                     @RequestParam(required = false) List<Long> tagIds) {

        val itemQueryParam = ItemQueryParam.builder()
                                           .itemType(type)
                                           .userId(userId)
                                           .status(status)
                                           .startTime(startTime)
                                           .endTime(endTime)
                                           .tagIds(tagIds)
                                           .pageNum(pageNum)
                                           .pageSize(pageSize)
                                           .build();

        val result = itemManager.queryItems(itemQueryParam);
        return ResponseEntity.ok(result);
    }
}
