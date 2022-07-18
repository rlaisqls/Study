package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public CreateItemResponse execute(CreateItemRequest request) {

        Item item = itemRepository.save(Item.builder()
                .name(request.getItemName())
                .price(request.getPrice())
                .stock(request.getStock())
                .build()
        );

        return new CreateItemResponse(item.getId());
    }
}