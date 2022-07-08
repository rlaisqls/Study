package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.DeleteItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemInfoRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemGroupResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemResponse;

public interface ItemService {
    CreateItemResponse createItem(CreateItemRequest request);

    void modifyItem(ModifyItemInfoRequest request);

    void addItemStock(Long id, AddItemStockRequest request);

    void deleteItem(DeleteItemRequest request);

    FindItemResponse findItemInfo(Long id);

    FindItemGroupResponse findItemGroup(int page, int size);
}