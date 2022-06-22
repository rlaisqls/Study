package com.practice.shoppingmall.entity.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}