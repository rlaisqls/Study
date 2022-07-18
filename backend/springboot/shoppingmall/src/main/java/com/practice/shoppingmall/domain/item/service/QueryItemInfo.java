package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import com.practice.shoppingmall.domain.item.presentation.dto.response.FindItemInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryItemInfo {

    private final ItemRepository itemRepository;

    public FindItemInfoResponse execute(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        return FindItemInfoResponse.of(item);
    }
}