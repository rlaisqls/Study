package com.practice.shoppingmall.service;

import com.practice.shoppingmall.dto.request.ItemRequest;
import com.practice.shoppingmall.dto.response.UuidResponse;
import com.practice.shoppingmall.entity.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public UuidResponse addItem(ItemRequest request){
        itemRepository.
    }

}