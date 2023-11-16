package com.bookstoreapi.repository;

import com.bookstoreapi.entity.OrderBook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

    List<OrderBook> findOrderBooksByOrderId(Long orderId);

}
