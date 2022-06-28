package com.practice.shoppingmall.entity.item;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ItemRepository extends PagingAndSortingRepository<Item, UUID> {

}