package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.AddItemStockRequest;
import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.ItemUuidResponse;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.exception.item.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public ItemUuidResponse addItem(CreateItemRequest request) {

        return new ItemUuidResponse(itemRepository.save(
                Item.builder()
                        .name(request.getName())
                        .price(request.getPrice())
                        .stock(request.getStock())
                        .build())
                .getId());
    }

    @Transactional
    public void modifyItemStock(ModifyItemInfoRequest request) {

        itemRepository.findById(request.getItemUuid())
                .ifPresentOrElse(
                        item -> {
                            item.modifyInfo(request.getName(), request.getPrice());
                            itemRepository.save(item);
                        },
                        () -> { throw ItemNotFoundException.EXCEPTION; }
                );
    }

    @Transactional
    public void addItemStock(AddItemStockRequest request) {

        itemRepository.findById(request.getItemUuid())
                .ifPresentOrElse(
                        item -> {
                            item.addStock(request.getAddItemStock());
                            itemRepository.save(item);
                        },
                        () -> { throw ItemNotFoundException.EXCEPTION; }
                );
    }

    @Transactional
    public void deleteItemStock(DeleteItemRequest request) {
        Item item = itemRepository.findById(request.getItemUuid())
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);
        itemRepository.delete(item);
    }


}