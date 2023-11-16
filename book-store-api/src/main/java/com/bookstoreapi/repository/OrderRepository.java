package com.bookstoreapi.repository;

import com.bookstoreapi.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserIdOrderByUpdatedAtDesc(Long userId);

}
