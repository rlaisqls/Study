package com.practice.shoppingmall.domain.delivery.domain.repository;

import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
}