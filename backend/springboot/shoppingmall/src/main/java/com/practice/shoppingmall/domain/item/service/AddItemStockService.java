package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddItemStockService {

    private final ItemRepository itemRepository;

    @Transactional
    public void execute(Long id, AddItemStockRequest request) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        item.addStock(request.getAddStock());

        itemRepository.save(item);
    }
}