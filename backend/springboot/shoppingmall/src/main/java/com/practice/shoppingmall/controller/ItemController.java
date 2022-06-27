package com.practice.shoppingmall.controller;

import com.practice.shoppingmall.dto.request.LoginRequest;
import com.practice.shoppingmall.dto.request.RegisterRequest;
import com.practice.shoppingmall.dto.request.item.AddItemStockRequest;
import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.TokenResponse;
import com.practice.shoppingmall.dto.response.item.ItemUuidResponse;
import com.practice.shoppingmall.dto.response.item.findItemResponse;
import com.practice.shoppingmall.service.ItemService;
import com.practice.shoppingmall.service.user.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item")
    public ItemUuidResponse createItem(@Valid @RequestBody CreateItemRequest request){
        return itemService.createItem(request);
    }

    @PatchMapping("/item")
    public void modifyItem(@Valid @RequestBody ModifyItemInfoRequest request){
        itemService.modifyItem(request);
    }

    @PostMapping("/item/add")
    public void addItemStock(@Valid @RequestBody AddItemStockRequest request){
        itemService.addItemStock(request);
    }

    @DeleteMapping("/item")
    public void deleteItemStock(@Valid @RequestBody DeleteItemRequest request){
        itemService.deleteItemStock(request);
    }

    @GetMapping("/item")
    public List<findItemResponse> findItemList(){
        return itemService.findItemList();
    }

    @GetMapping("/item/{itemUuid}")
    public findItemResponse findItem(@PathVariable String itemUuid){
        return itemService.findItem(itemUuid);
    }
}