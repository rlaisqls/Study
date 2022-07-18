package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ModifyItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void execute(ModifyItemRequest request) {

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> ItemNotFoundException.EXCEPTION);

        item.modifyInfo(request.getItemName(), request.getPrice());

        itemRepository.save(item);
    }
}