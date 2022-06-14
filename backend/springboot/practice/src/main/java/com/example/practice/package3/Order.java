package com.example.practice.package3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Order {
    String deliveryState;
    LocalDate placedOn;
}