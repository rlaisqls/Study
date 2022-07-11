package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemInfoResponse;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public CreateItemResponse createItem(CreateItemRequest request) {

        Item item = itemRepository.save(Item.builder()
                .name(request.getItemName())
                .price(request.getPrice())
                .stock(request.getStock())
                .build()
        );

        return new CreateItemResponse(item.getId());
    }

    @Override
    @Transactional
    public void modifyItem(ModifyItemRequest request) {

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        item.modifyInfo(request.getItemName(), request.getPrice());

        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void addItemStock(Long id, AddItemStockRequest request) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        item.addStock(request.getAddStock());

        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        itemRepository.delete(item);
    }

    @Override
    public FindItemInfoResponse findItemInfo(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        return FindItemInfoResponse.of(item);
    }

    @Override
    public FindItemListResponse findItemList() {

        List<Item> itemPage = itemRepository.findBy();

        return FindItemListResponse.of(itemPage);
    }

}