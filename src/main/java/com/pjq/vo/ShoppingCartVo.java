package com.pjq.vo;

import lombok.Data;

@Data
public class ShoppingCartVo {
    private String username;
    private int product_id;
    private double price;
    private int number;
    private String product_name;
}
