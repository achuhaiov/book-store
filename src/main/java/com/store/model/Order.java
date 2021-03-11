package com.store.model;

import lombok.Data;

@Data
public class Order {
    private long id;
    private long bookId;
    private String username;
    private int quantity;

    public Order(long bookId, String username, int quantity) {
        this.bookId = bookId;
        this.username = username;
        this.quantity = quantity;
    }
}
