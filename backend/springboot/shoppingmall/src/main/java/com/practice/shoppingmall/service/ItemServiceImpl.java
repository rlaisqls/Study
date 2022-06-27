package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.item.AddItemStockRequest;
import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.CreateItemResponse;
import com.practice.shoppingmall.dto.response.item.FindItemResponse;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.exception.item.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public CreateItemResponse createItem(CreateItemRequest request) {

        return new CreateItemResponse(itemRepository.save(
                        Item.builder()
                                .name(request.getName())
                                .price(request.getPrice())
                                .stock(request.getStock())
                                .build())
                .getId());
    }

    @Override
    @Transactional
    public void patchItem(ModifyItemInfoRequest request) {

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
    public FindItemResponse findItem(String itemUuid) {

        Item item = itemRepository.findById(UUID.fromString(itemUuid))
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        return getFindItemResponses(item);
    }

    @Override
    public List<FindItemResponse> findItemList() {

        List<Item> itemList = itemRepository.findAll();

        return itemList
                .stream()
                .map(this::getFindItemResponses)
                .collect(Collectors.toList());
    }

    private FindItemResponse getFindItemResponses(Item item) {

        return FindItemResponse
                .builder()
                .uuid(item.getId().toString())
                .name(item.getName())
                .price(item.getPrice())
                .stock(item.getStock())
                .isSoldOut(item.getStock() == 0)
                .build();
    }

}