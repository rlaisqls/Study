package com.practice.shoppingmall.domain.item.presentation;

import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemGroupResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemResponse;
import com.practice.shoppingmall.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    public CreateItemResponse createItem(@Valid @RequestBody CreateItemRequest request){
        return itemService.createItem(request);
    }

    @PatchMapping("/item")
    public void patchItem(@Valid @RequestBody ModifyItemRequest request){
        itemService.modifyItem(request);
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
    }

    @PutMapping("/item/{itemId}")
    public void addItemStock(@PathVariable Long itemId, @Valid @RequestBody AddItemStockRequest request){
        itemService.addItemStock(itemId, request);
    }

    @GetMapping("/item")
    public FindItemGroupResponse findItemGroup(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return itemService.findItemGroup(page, size);
    }

    @GetMapping("/item/{itemId}")
    public FindItemResponse findItemInfo(@PathVariable Long itemId){
        return itemService.findItemInfo(itemId);
    }
}