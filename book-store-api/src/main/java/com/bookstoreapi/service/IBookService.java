package com.bookstoreapi.service;

import com.bookstoreapi.dao.BookDAO;
import org.springframework.data.domain.Page;

public interface IBookService {
    BookDAO save(BookDAO book);

    BookDAO update(String ISBN, BookDAO book);

    void delete(String ISBN);

    Page<BookDAO> getAll(int page, int limit);

    BookDAO getByISBN(String ISBN);

}
