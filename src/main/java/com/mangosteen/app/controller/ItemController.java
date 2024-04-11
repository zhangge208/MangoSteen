package com.mangosteen.app.controller;

import java.util.Objects;

import com.mangosteen.app.annotation.CurrentUserId;
import com.mangosteen.app.exception.InvalidParameterException;
import com.mangosteen.app.manager.ItemManager;
import com.mangosteen.app.model.vo.ItemVO;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * @param id the item id
     * @param userId user id
     * @return item vo.
     */
    @GetMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public ItemVO getItemById(@PathVariable("id") Long id,
                              @CurrentUserId Long userId) {
        if (id == null || id <= 0L) {
            throw new InvalidParameterException("The item id must be positive.");
        }
        val result = itemManager.getItemByItemId(id);

        if (!Objects.equals(result.getUserId(), userId)) {
            throw new InvalidParameterException("The user id and item id are mismatched.");
        }

        return result;
    }
}
