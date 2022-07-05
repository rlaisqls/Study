package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemInfoRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.DeleteItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemGroupResponse;

import java.util.UUID;

public interface ItemService {
    CreateItemResponse createItem(CreateItemRequest request);

    void modifyItem(ModifyItemInfoRequest request);

    void addItemStock(UUID id, Integer addStock);

    void deleteItem(DeleteItemRequest request);

    FindItemResponse findItem(UUID id);

    FindItemGroupResponse findItemList(int page, int size);
}