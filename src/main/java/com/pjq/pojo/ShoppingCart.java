package com.pjq.pojo;

import lombok.Data;

@Data
public class ShoppingCart {
    private String username;
    private int product_id;
    private double price;
    private int number;
}
