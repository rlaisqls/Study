package com.practice.shoppingmall.domain.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.shoppingmall.domain.coupon.domain.Coupon;
import com.practice.shoppingmall.domain.item.domain.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orders;

    private int orderPrice;

    private int count;

    private int totalPrice;

    public void cancel() {
        getItem().addStock(count);
    }

    public void applyCoupon(Coupon coupon) {
        this.totalPrice = coupon.doDiscount(this.totalPrice);
    }
}