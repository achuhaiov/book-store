package com.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String isbn;
    private BigDecimal price;
    private int availableQuantity;
}
