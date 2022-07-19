package com.practice.shoppingmall.domain.delivery.domain;

import com.practice.shoppingmall.domain.order.domain.Order;
import com.practice.shoppingmall.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order orders;

    @NotNull
    @Size(max = 60)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DeliveryStatus deliveryStatus;

    public static Delivery start(User user){
        return Delivery
                .builder()
                .address(user.getAddress())
                .deliveryStatus(DeliveryStatus.READY)
                .build();
    }
}