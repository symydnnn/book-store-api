package com.bookstoreapi.service;

import com.bookstoreapi.entity.OrderBook;
import com.bookstoreapi.repository.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderBookService implements IOrderBookService {

    private final OrderBookRepository orderBookRepository;

    @Override
    public OrderBook save(OrderBook orderBook) {
        return orderBookRepository.save(orderBook);

    }

}
