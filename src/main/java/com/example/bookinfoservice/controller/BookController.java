package com.example.bookinfoservice.controller;

import ch.qos.logback.core.sift.AppenderTracker;
import com.example.bookinfoservice.model.Book;
import com.example.bookinfoservice.repository.BookRepository;
import org.hibernate.result.UpdateCountOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void fillDB(){
        if(bookRepository.count()==0){
            bookRepository.save(new Book("Test boek 1", "687468435454"));
            bookRepository.save(new Book("Test boek 2", "687468434567"));
        }

        System.out.println(bookRepository.findBookByISBN("687468435454").getTitle());
    }

    @GetMapping("/books/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        return bookRepository.findBooksByTitleContaining(title);
    }

    @GetMapping("/books/{ISBN}")
    public Book getBookByISBN(@PathVariable String ISBN){
        return bookRepository.findBookByISBN(ISBN);
    }

}
