package com.bookstoreapi.service;

import com.bookstoreapi.dao.OrderDAO;
import com.bookstoreapi.dao.OrderRequest;
import java.util.List;

public interface IOrderService {

    OrderDAO save(OrderRequest orderRequest);

    List<OrderDAO> getAllByUserId(long userId);

    OrderDAO getById(long orderId);
}
