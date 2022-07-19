package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.facade.ItemFacade;
import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ModifyItemStockService {

    private final ItemRepository itemRepository;

    private final ItemFacade itemFacade;

    @Transactional
    public void execute(Long itemId, AddItemStockRequest request) {

        Item item = itemFacade.getItemById(itemId);

        item.addStock(request.getAddStock());

        itemRepository.save(item);
    }
}