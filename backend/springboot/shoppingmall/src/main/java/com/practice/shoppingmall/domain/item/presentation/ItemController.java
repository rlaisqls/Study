package com.practice.shoppingmall.domain.item.presentation;

import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.QueryItemInfoResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.QueryItemListResponse;
import com.practice.shoppingmall.domain.item.service.ModifyItemStockService;
import com.practice.shoppingmall.domain.item.service.CreateItemService;
import com.practice.shoppingmall.domain.item.service.DeleteItemService;
import com.practice.shoppingmall.domain.item.service.ModifyItemService;
import com.practice.shoppingmall.domain.item.service.QueryItemInfoService;
import com.practice.shoppingmall.domain.item.service.QueryItemListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final CreateItemService createItemService;

    private final ModifyItemService modifyItemService;

    private final ModifyItemStockService modifyItemStockService;

    private final DeleteItemService deleteItemService;

    private final QueryItemInfoService queryItemInfoService;

    private final QueryItemListService queryItemListService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateItemResponse createItem(@Valid @RequestBody CreateItemRequest request){
        return createItemService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{item-id}")
    public void modifyItem(@PathVariable("item-id") Long itemId, @Valid @RequestBody ModifyItemRequest request){
        modifyItemService.execute(itemId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/stock/{item-id}")
    public void addItemStock(@PathVariable("item-id") Long itemId, @Valid @RequestBody AddItemStockRequest request){
        modifyItemStockService.execute(itemId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{item-id}")
    public void deleteItem(@PathVariable("item-id") Long itemId){
        deleteItemService.execute(itemId);
    }


    @GetMapping
    public QueryItemListResponse queryItemList(){
        return queryItemListService.execute();
    }

    @GetMapping("/{itemId}")
    public QueryItemInfoResponse queryItemInfo(@PathVariable Long itemId){
        return queryItemInfoService.execute(itemId);
    }
}