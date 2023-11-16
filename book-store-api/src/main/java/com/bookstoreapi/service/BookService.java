package com.bookstoreapi.service;

import com.bookstoreapi.dao.BookDAO;
import com.bookstoreapi.entity.Book;
import com.bookstoreapi.enums.MessageTypeEnums;
import com.bookstoreapi.exception.BookISBNAlreadyExistsException;
import com.bookstoreapi.exception.BookNotFoundException;
import com.bookstoreapi.repository.BookRepository;
import com.bookstoreapi.utils.GeneratorUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final ObjectMapper mapper;
    private static final String sortByCreatedAtDesc = "-createdAt";

    @Override
    public BookDAO save(BookDAO bookDAO) {
        if (bookDAO.getISBN() != null && bookRepository.existsByISBN(bookDAO.getISBN())) {
            throw new BookISBNAlreadyExistsException(MessageTypeEnums.BOOK_ISBN_ALREADY_EXISTS.getMessage());
        }
        String ISBN = bookDAO.getISBN();

        if (ISBN == null) {
            do {
                ISBN = GeneratorUtils.generateISBN();
            } while (bookRepository.existsByISBN(ISBN));
        }

        Book newBook = bookRepository.save(Book.builder()
                .ISBN(ISBN)
                .title(bookDAO.getTitle())
                .author(bookDAO.getAuthor())
                .price(bookDAO.getPrice())
                .stockQuantity(bookDAO.getStockQuantity())
                .createdAt(new Date())
                .build());

        log.info("BookService - save : bookDAO info saved. Book ISBN is {} and title is {}", newBook.getISBN(), newBook.getTitle());
        return mapper.convertValue(newBook, BookDAO.class);
    }

    @Override
    public BookDAO update(String ISBN, BookDAO bookDAO) {

        Book oldBook = bookRepository.findBookByISBN(ISBN);
        if (ObjectUtils.isEmpty(oldBook)) {
            throw new BookNotFoundException(MessageTypeEnums.BOOK_NOT_FOUND.getMessage());
        }
        oldBook.setTitle(bookDAO.getTitle());
        oldBook.setAuthor(bookDAO.getAuthor());
        oldBook.setPrice(bookDAO.getPrice());
        oldBook.setStockQuantity(bookDAO.getStockQuantity());
        Book newBook = bookRepository.save(oldBook);

        log.info("BookService - update : bookDAO info updated. Book ISBN is {} and title is {}", newBook.getISBN(), newBook.getTitle());
        return mapper.convertValue(newBook, BookDAO.class);
    }

    @Override
    public void delete(String ISBN) {
        Book book = bookRepository.findBookByISBN(ISBN);
        if (ObjectUtils.isEmpty(book)) {
            throw new BookNotFoundException(MessageTypeEnums.BOOK_NOT_FOUND.getMessage());
        }
        bookRepository.delete(mapper.convertValue(book, Book.class));
        log.info("BookService - delete : book delete. Book ISBN was {}.", ISBN);
    }

    @Override
    public Page<BookDAO> getAll(int page, int limit) {

        Page<Book> books = bookRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, limit));
        if (!ObjectUtils.isEmpty(books.getContent())) {
            log.info("BookService - getAll : books are getting. Total books size is {}.", books.getTotalElements());
            return books.map(objectEntity -> mapper.convertValue(objectEntity, BookDAO.class));
        }
        return null;
    }

    @Override
    public BookDAO getByISBN(String ISBN) {
        Book book = bookRepository.findBookByISBN(ISBN);
        if (ObjectUtils.isEmpty(book)) {
            throw new BookNotFoundException(MessageTypeEnums.BOOK_NOT_FOUND.getMessage());
        }
        log.info("BookService - getAll : book is getting. Book ISBN is {} and title is {}", book.getISBN(), book.getTitle());
        return mapper.convertValue(book, BookDAO.class);
    }
}
