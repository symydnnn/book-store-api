package com.bookstoreapi.service;

import com.bookstoreapi.entity.OrderBook;

public interface IOrderBookService {
    OrderBook save(OrderBook orderBook);
}
