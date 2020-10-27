package com.example.bookinfoservice.repository;

import com.example.bookinfoservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByISBN(String ISBN);
    List<Book> findBooksByTitleContaining(String title);
}
