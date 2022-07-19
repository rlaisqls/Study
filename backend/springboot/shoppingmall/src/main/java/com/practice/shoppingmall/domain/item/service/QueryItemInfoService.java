package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.facade.ItemFacade;
import com.practice.shoppingmall.domain.item.presentation.dto.response.QueryItemInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryItemInfoService {

    private final ItemFacade itemFacade;

    public QueryItemInfoResponse execute(Long itemId) {

        Item item = itemFacade.getItemById(itemId);

        return QueryItemInfoResponse.of(item);
    }
}