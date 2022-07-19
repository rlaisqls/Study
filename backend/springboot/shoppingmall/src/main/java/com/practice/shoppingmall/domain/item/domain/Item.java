package com.practice.shoppingmall.domain.item.domain;

import com.practice.shoppingmall.domain.item.exception.NotEnoughStockException;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private int price;

    @NotNull
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

    public void modifyInfo(ModifyItemRequest request) {
        this.name = request.getItemName();
        this.price = request.getPrice();
    }
}