package com.bookstoreapi.repository;

import com.bookstoreapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Book findBookByISBN(String ISBN);

    boolean existsByISBN(String ISBN);

    Page<Book> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
