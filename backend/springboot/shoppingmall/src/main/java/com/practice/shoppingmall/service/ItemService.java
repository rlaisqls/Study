package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.AddItemStockRequest;
import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.ItemUuidResponse;
import com.practice.shoppingmall.dto.response.item.findItemResponse;
import org.springframework.transaction.annotation.Transactional;

public interface ItemService {
    ItemUuidResponse createItem(CreateItemRequest request);

    void modifyItem(ModifyItemInfoRequest request);

    void addItemStock(AddItemStockRequest request);

    void deleteItemStock(DeleteItemRequest request);

    findItemResponse findItem(String itemUuid);
}