package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemInfoRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.DeleteItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemGroupResponse;
import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.global.exception.item.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public CreateItemResponse createItem(CreateItemRequest request) {

        Item item = itemRepository.save(Item.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .build()
        );

        return new CreateItemResponse(item.getId().toString());
    }

    @Override
    @Transactional
    public void modifyItem(ModifyItemInfoRequest request) {

        Item item = itemRepository.findById(request.getItemUuid())
                .orElseThrow(()->ItemNotFoundException.EXCEPTION);

        item.modifyInfo(request.getName(), request.getPrice());
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void addItemStock(UUID id, Integer addStock) {

        Item item = itemRepository.findById(id)
                .orElseThrow(()->ItemNotFoundException.EXCEPTION);

        item.addStock(addStock);
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteItem(DeleteItemRequest request) {

        Item item = itemRepository.findById(request.getItemUuid())
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        itemRepository.delete(item);
    }

    @Override
    public FindItemResponse findItem(UUID id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        return FindItemResponse.of(item);
    }

    @Override
    public FindItemGroupResponse findItemList(int page, int size) {

        Pageable request = PageRequest.of(page, size);
        Page<Item> itemPage = itemRepository.findAll(request);

        return FindItemGroupResponse.of(itemPage);
    }

}