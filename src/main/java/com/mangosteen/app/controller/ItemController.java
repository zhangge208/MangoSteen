package com.mangosteen.app.controller;

import com.mangosteen.app.annotation.CurrentUserId;
import com.mangosteen.app.manager.ItemManager;
import com.mangosteen.app.model.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

}
