package com.store.controller;

import com.store.dto.IdResponse;
import com.store.dto.OrderRequest;
import com.store.model.OrderInfo;
import com.store.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BookStoreController {
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<IdResponse> createOrder(Principal principal, @Valid @RequestBody OrderRequest orderRequest) {
        Long id = orderService.createOrder(principal.getName(), orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdResponse(id));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderInfo>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}
