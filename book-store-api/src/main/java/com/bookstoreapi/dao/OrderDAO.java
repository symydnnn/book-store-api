package com.bookstoreapi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDAO {

    Long id;
    UserDAO user;
    List<BookDAO> listOfBooks;
    BigDecimal totalPrice;
    Date orderDate;
    Date createdAt;
    Date updatedAt;
}
