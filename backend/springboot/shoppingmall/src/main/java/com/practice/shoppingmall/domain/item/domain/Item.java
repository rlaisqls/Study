package com.practice.shoppingmall.domain.item.domain;

import com.practice.shoppingmall.global.exception.item.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    private String name;

    private int price;

    private int stock;

    public void addStock(int quantity){
        this.stock += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stock - quantity;
        if(restStock < 0) {
            throw NotEnoughStockException.EXCEPTION;
        }
        this.stock = restStock;
    }

    public void modifyInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }
}