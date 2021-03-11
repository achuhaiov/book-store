package com.store.service;

import com.store.dto.OrderRequest;
import com.store.model.OrderInfo;

import java.util.List;

public interface OrderService {

    Long createOrder(String username, OrderRequest orderRequest);

    List<OrderInfo> getOrders();

}
