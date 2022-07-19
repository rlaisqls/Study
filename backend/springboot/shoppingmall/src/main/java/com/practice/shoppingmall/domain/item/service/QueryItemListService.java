package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.presentation.dto.response.QueryItemListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryItemListService {

    private final ItemRepository itemRepository;

    public QueryItemListResponse execute() {

        List<Item> itemList = itemRepository.findBy();

        return QueryItemListResponse.of(itemList);
    }
}