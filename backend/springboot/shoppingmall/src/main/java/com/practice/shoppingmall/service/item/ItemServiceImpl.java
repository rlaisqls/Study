package com.practice.shoppingmall.service.item;

import com.practice.shoppingmall.dto.request.item.CreateItemRequest;
import com.practice.shoppingmall.dto.request.item.DeleteItemRequest;
import com.practice.shoppingmall.dto.request.item.ModifyItemInfoRequest;
import com.practice.shoppingmall.dto.response.item.CreateItemResponse;
import com.practice.shoppingmall.dto.response.item.FindItemGroupResponse;
import com.practice.shoppingmall.dto.response.item.FindItemResponse;
import com.practice.shoppingmall.entity.item.Item;
import com.practice.shoppingmall.entity.item.ItemRepository;
import com.practice.shoppingmall.exception.item.ItemNotFoundException;
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

        return new CreateItemResponse(item.getId());
    }

    @Override
    @Transactional
    public void modifyItem(ModifyItemInfoRequest request) {

        Item item = itemRepository.findById(UUID.fromString(request.getItemUuid()))
                .orElseThrow(()->ItemNotFoundException.EXCEPTION);

        item.modifyInfo(request.getName(), request.getPrice());
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void addItemStock(String uuid, Integer addStock) {

        Item item = itemRepository.findById(UUID.fromString(uuid))
                .orElseThrow(()->ItemNotFoundException.EXCEPTION);

        item.addStock(addStock);
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteItem(DeleteItemRequest request) {

        Item item = itemRepository.findById(UUID.fromString(request.getItemUuid()))
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        itemRepository.delete(item);
    }

    @Override
    public FindItemResponse findItem(String uuid) {

        Item item = itemRepository.findById(UUID.fromString(uuid))
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