package com.store.service.impl;

import com.store.dto.OrderRequest;
import com.store.exception.BookNotFoundException;
import com.store.exception.UnavailableBookQuantityException;
import com.store.model.Book;
import com.store.model.Order;
import com.store.model.OrderInfo;
import com.store.repository.BookRepository;
import com.store.repository.OrderRepository;
import com.store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Override
    public Long createOrder(String username, OrderRequest orderRequest) {
        Book book = bookRepository.getBook(orderRequest.getIsbn())
                .orElseThrow(() -> new BookNotFoundException(
                        String.format("Book with ISBN: %s not found", orderRequest.getIsbn())));

        if (book.getAvailableQuantity() >= orderRequest.getQuantity()) {
            bookRepository.subtractAvailableQuantityByBookId(book.getId(), orderRequest.getQuantity());
        } else {
            throw new UnavailableBookQuantityException(
                    book.getAvailableQuantity() > 0 ?
                            String.format("There are only %s book(s) with ISBN %s", book.getAvailableQuantity(), orderRequest.getIsbn())
                            : String.format("There are no books with ISBN %s", orderRequest.getIsbn())
            );
        }

        return orderRepository.insert(
                new Order(book.getId(), username, orderRequest.getQuantity())
        );
    }

    @Override
    public List<OrderInfo> getOrders() {
        return orderRepository.getOrders();
    }
}
