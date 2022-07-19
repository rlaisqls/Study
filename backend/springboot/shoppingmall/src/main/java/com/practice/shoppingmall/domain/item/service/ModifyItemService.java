package com.practice.shoppingmall.domain.item.service;

import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.facade.ItemFacade;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ModifyItemService {

    private final ItemRepository itemRepository;

    private final ItemFacade itemFacade;

    @Transactional
    public void execute(Long itemId, ModifyItemRequest request) {

        Item item = itemFacade.getItemById(itemId);

        item.modifyInfo(request);

        itemRepository.save(item);
    }
}