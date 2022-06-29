package com.practice.shoppingmall.service;

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

import java.util.List;
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

        return getFindItemResponses(item);
    }

    @Override
    public FindItemGroupResponse findItemList(int page, int size) {
        Pageable request = PageRequest.of(page, size);
        Page<Item> itemPage = itemRepository.findAll(request);

        List<FindItemResponse> itemResponseList = itemPage
                .map(FindItemResponse::of).toList();

        return FindItemGroupResponse
                .builder()
                .itemResponseList(itemResponseList)
                .totalPage(itemPage.getTotalPages())
                .totalSize(itemPage.getTotalElements()).build();
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