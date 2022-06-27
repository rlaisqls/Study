package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.AddItemStockRequest;
import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.CreateItemResponse;
import com.practice.shoppingmall.dto.response.item.FindItemResponse;

import java.util.List;

public interface ItemService {
    CreateItemResponse createItem(CreateItemRequest request);

    void patchItem(ModifyItemInfoRequest request);

    void addItemStock(AddItemStockRequest request);

    void deleteItemStock(DeleteItemRequest request);

    FindItemResponse findItem(String itemUuid);

    List<FindItemResponse> findItemList();
}