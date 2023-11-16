package com.bookstoreapi.controller;

import com.bookstoreapi.dao.BookDAO;
import com.bookstoreapi.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "books", description = "The Books API")
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @Operation(description = "Get All Books", tags = {"books"})
    @GetMapping
    public ResponseEntity<Page<BookDAO>> getAll(@RequestParam int page, @RequestParam int limit) {
        Page<BookDAO> bookDAOS = bookService.getAll(page, limit);
        return new ResponseEntity<>(bookDAOS, HttpStatus.OK);
    }

    @Operation(description = "Get Book By ISBN", tags = {"books"})
    @GetMapping("/{isbn}")
    public ResponseEntity<BookDAO> getByISBN(@PathVariable("isbn") String ISBN) {

        BookDAO bookDAO = bookService.getByISBN(ISBN);
        return new ResponseEntity<>(bookDAO, HttpStatus.OK);
    }

    @Operation(description = "Save a Book", tags = {"books"})
    @PostMapping
    public ResponseEntity<BookDAO> save(@RequestBody BookDAO bookDAO) {

        BookDAO newBookDAO = bookService.save(bookDAO);
        return new ResponseEntity<>(newBookDAO, HttpStatus.OK);
    }


    @Operation(description = "Update a Book", tags = {"books"})
    @PutMapping("/{isbn}")
    public ResponseEntity<BookDAO> update(@PathVariable("isbn") String ISBN, @RequestBody BookDAO book) {

        BookDAO bookDAO = bookService.update(ISBN, book);
        return new ResponseEntity<>(bookDAO, HttpStatus.OK);
    }


    @Operation(description = "Delete a Book By ISBN", tags = {"books"})
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> delete(@PathVariable("isbn") String ISBN) {

        bookService.delete(ISBN);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
