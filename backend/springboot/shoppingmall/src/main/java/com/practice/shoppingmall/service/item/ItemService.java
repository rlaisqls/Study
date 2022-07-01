package com.practice.shoppingmall.service.item;

import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.CreateItemResponse;
import com.practice.shoppingmall.dto.response.item.FindItemGroupResponse;
import com.practice.shoppingmall.dto.response.item.FindItemResponse;

import java.util.UUID;

public interface ItemService {
    CreateItemResponse createItem(CreateItemRequest request);

    void modifyItem(ModifyItemInfoRequest request);

    void addItemStock(UUID id, Integer addStock);

    void deleteItem(DeleteItemRequest request);

    FindItemResponse findItem(UUID id);

    FindItemGroupResponse findItemList(int page, int size);
}