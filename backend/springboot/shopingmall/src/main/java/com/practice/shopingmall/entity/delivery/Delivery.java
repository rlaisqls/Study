package com.practice.shopingmall.entity.delivery;

import com.practice.shopingmall.entity.order.Order;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @Column(name = "delivery_id", columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order orders;

    private String address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}