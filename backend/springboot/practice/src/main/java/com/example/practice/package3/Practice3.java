package com.example.practice.package3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Practice3 {

    public LocalDate deliveryDate(Order order, boolean isRush) {
        if (isRush) {
            return getLocalDate(order);
        } else {
            int deliveryTime = switch (order.getDeliveryState()) {
                case "WA", "CA" -> 2;
                case "OR", "TX", "NY" -> 3;
                default -> 4;
            };
            return order.getPlacedOn().plusDays(deliveryTime);
        }
    }

    private LocalDate getLocalDate(Order order) {
        int deliveryTime = switch (order.getDeliveryState()) {
            case "WA", "CA", "OR" -> 1;
            case "TX", "NY", "FL" -> 2;
            default -> 3;
        };
        return order.getPlacedOn().plusDays(deliveryTime);
    }
}