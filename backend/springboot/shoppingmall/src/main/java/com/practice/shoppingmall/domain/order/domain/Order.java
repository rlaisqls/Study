package com.practice.shoppingmall.domain.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.shoppingmall.domain.delivery.domain.Delivery;
import com.practice.shoppingmall.domain.delivery.domain.DeliveryStatus;
import com.practice.shoppingmall.domain.order.exception.AlreadyDeliveredException;
import com.practice.shoppingmall.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @NotNull
    private LocalDateTime orderDateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private OrderStatus orderStatus;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    public static Order createOrder(User user, Delivery delivery, List<OrderItem> orderItems) {

        Order order = Order.builder()
                .user(user)
                .delivery(delivery)
                .orderItems(new ArrayList<>())
                .orderStatus(OrderStatus.ORDER)
                .orderDateTime(LocalDateTime.now())
                .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void cancel() {

        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw AlreadyDeliveredException.EXCEPTION;
        }

        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}