package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.CreateItemResponse;
import com.practice.shoppingmall.dto.response.item.FindItemGroupResponse;
import com.practice.shoppingmall.dto.response.item.FindItemResponse;

public interface ItemService {
    CreateItemResponse createItem(CreateItemRequest request);

    void modifyItem(ModifyItemInfoRequest request);

    void addItemStock(String uuid, Integer addStock);

    void deleteItem(DeleteItemRequest request);

    FindItemResponse findItem(String uuid);

    FindItemGroupResponse findItemList(int page, int size);
}