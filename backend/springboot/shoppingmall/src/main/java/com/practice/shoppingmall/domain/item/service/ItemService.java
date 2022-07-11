package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemListResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemInfoResponse;

public interface ItemService {
    CreateItemResponse createItem(CreateItemRequest request);

    void modifyItem(ModifyItemRequest request);

    void addItemStock(Long id, AddItemStockRequest request);

    void deleteItem(Long id);

    FindItemInfoResponse findItemInfo(Long id);

    FindItemListResponse findItemList();
}