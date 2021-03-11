package com.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private long id;
    private String isbn;
    private String username;
    private int quantity;
    private BigDecimal sumPrice;
}
