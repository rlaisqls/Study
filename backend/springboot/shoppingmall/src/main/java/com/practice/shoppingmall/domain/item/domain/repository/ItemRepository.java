package com.practice.shoppingmall.domain.item.domain.repository;

import com.practice.shoppingmall.domain.item.domain.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

}