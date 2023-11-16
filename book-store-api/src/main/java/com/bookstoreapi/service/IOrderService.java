package com.bookstoreapi.service;

import com.bookstoreapi.dao.OrderDAO;
import java.util.List;

public interface IOrderService {

    OrderDAO save(List<String> bookISBNList);

    List<OrderDAO> getAllByUserId(long userId);

    OrderDAO getById(long orderId);
}
