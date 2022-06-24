package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.AddItemStockRequest;
import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.ItemUuidResponse;
import com.practice.shoppingmall.dto.response.item.findItemResponse;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.exception.item.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemUuidResponse createItem(CreateItemRequest request) {

        return new ItemUuidResponse(itemRepository.save(
                        Item.builder()
                                .name(request.getName())
                                .price(request.getPrice())
                                .stock(request.getStock())
                                .build())
                .getId());
    }

    @Override
    @Transactional
    public void modifyItem(ModifyItemInfoRequest request) {

        itemRepository.findById(UUID.fromString(request.getItemUuid()))
                .ifPresentOrElse(
                        item -> {
                            item.modifyInfo(request.getName(), request.getPrice());
                            itemRepository.save(item);
                        },
                        () -> {
                            throw ItemNotFoundException.EXCEPTION;
                        }
                );
    }

    @Override
    @Transactional
    public void addItemStock(AddItemStockRequest request) {

        itemRepository.findById(UUID.fromString(request.getItemUuid()))
                .ifPresentOrElse(
                        item -> {
                            item.addStock(request.getAddItemStock());
                            itemRepository.save(item);
                        },
                        () -> {
                            throw ItemNotFoundException.EXCEPTION;
                        }
                );
    }

    @Override
    @Transactional
    public void deleteItemStock(DeleteItemRequest request) {
        Item item = itemRepository.findById(UUID.fromString(request.getItemUuid()))
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);
        itemRepository.delete(item);
    }

    @Override
    public findItemResponse findItem(String itemUuid) {

        Item item = itemRepository.findById(UUID.fromString(itemUuid))
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        return findItemResponse
                .builder()
                .name(item.getName())
                .price(item.getPrice())
                .isSoldOut(item.getStock()==0)
                .build();
    }


}