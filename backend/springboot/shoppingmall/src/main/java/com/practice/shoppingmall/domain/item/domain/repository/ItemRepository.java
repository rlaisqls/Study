package com.practice.shoppingmall.domain.item.domain.repository;

import com.practice.shoppingmall.domain.item.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findBy();
}