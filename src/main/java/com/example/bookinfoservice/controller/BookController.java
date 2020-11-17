package com.example.bookinfoservice.controller;

import com.example.bookinfoservice.model.Book;
import com.example.bookinfoservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        return bookRepository.findBooksByTitleContaining(title);
    }

    @GetMapping("/books/{ISBN}")
    public Book getBookByISBN(@PathVariable String ISBN){
        return bookRepository.findBookByISBN(ISBN);
    }
    
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

}
