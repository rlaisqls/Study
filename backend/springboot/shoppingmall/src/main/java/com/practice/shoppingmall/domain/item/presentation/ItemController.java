package com.practice.shoppingmall.domain.item.presentation;

import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemInfoRequest;
import com.practice.shoppingmall.domain.item.service.ItemService;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.DeleteItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    public ResponseBody createItem(@Valid @RequestBody CreateItemRequest request){
        return ResponseBody.of(itemService.createItem(request), HttpStatus.CREATED.value());
    }

    @PatchMapping("/item")
    public void patchItem(@Valid @RequestBody ModifyItemInfoRequest request){
        itemService.modifyItem(request);
    }

    @DeleteMapping("/item")
    public void deleteItem(@Valid @RequestBody DeleteItemRequest request){
        itemService.deleteItem(request);
    }

    @PostMapping("/item/{itemId}/{addStock}")
    public void addItemStock(@PathVariable UUID itemId, @PathVariable Integer addStock){
        itemService.addItemStock(itemId, addStock);
    }

    @GetMapping("/item")
    public ResponseBody findItemList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return ResponseBody.of(itemService.findItemList(page, size), HttpStatus.OK.value());
    }

    @GetMapping("/item/{itemId}")
    public ResponseBody findItem(@PathVariable UUID itemId){
        return ResponseBody.of(itemService.findItem(itemId), HttpStatus.OK.value());
    }
}