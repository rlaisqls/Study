package com.practice.shoppingmall.entity.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @Column(name = "item_id", columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String name;

    private int price;

    private int stock;

    //데이터를 가지고 있는 쪽에 비즈니스 메서드가 있는게 객체지향적으로 더 나음
    public void addStock(int quantity){
        this.stock += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stock - quantity;
        //if(restStock < 0) throw OutOfStockException.EXCEPTION;
        this.stock = restStock;
    }
}