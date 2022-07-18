package com.practice.shoppingmall.domain.item.presentation;

import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemInfoResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemListResponse;
import com.practice.shoppingmall.domain.item.service.AddItemStockService;
import com.practice.shoppingmall.domain.item.service.CreateItemService;
import com.practice.shoppingmall.domain.item.service.DeleteItemService;
import com.practice.shoppingmall.domain.item.service.ModifyItemService;
import com.practice.shoppingmall.domain.item.service.QueryItemInfo;
import com.practice.shoppingmall.domain.item.service.QueryItemListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final AddItemStockService addItemStockService;

    private final CreateItemService createItemService;

    private final DeleteItemService deleteItemService;

    private final ModifyItemService modifyItemService;

    private final QueryItemInfo queryItemInfoService;

    private final QueryItemListService queryItemListService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    public CreateItemResponse createItem(@Valid @RequestBody CreateItemRequest request){
        return createItemService.execute(request);
    }

    @PatchMapping("/item")
    public void patchItem(@Valid @RequestBody ModifyItemRequest request){
        modifyItemService.execute(request);
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable Long itemId){
        deleteItemService.execute(itemId);
    }

    @PutMapping("/item/{itemId}")
    public void addItemStock(@PathVariable Long itemId, @Valid @RequestBody AddItemStockRequest request){
        addItemStockService.execute(itemId, request);
    }

    @GetMapping("/item")
    public FindItemListResponse findItemGroup(){
        return queryItemListService.execute();
    }

    @GetMapping("/item/{itemId}")
    public FindItemInfoResponse findItemInfo(@PathVariable Long itemId){
        return queryItemInfoService.execute(itemId);
    }
}