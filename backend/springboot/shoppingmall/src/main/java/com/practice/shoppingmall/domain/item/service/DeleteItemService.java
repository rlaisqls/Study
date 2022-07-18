package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void execute(Long id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        itemRepository.delete(item);
    }
}