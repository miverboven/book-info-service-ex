package com.example.bookinfoservice;

import com.example.bookinfoservice.model.Book;
import com.example.bookinfoservice.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private Book book1 = new Book("Book1", "ISBN1");
    private Book book2 = new Book("Book2", "ISBN2");

    private List<Book> allBooks = Arrays.asList(book1, book2);

    @Test
    public void givenBook_whenGetBookByISBN_thenReturnJsonBook() throws Exception {

        given(bookRepository.findBookByISBN("ISBN1")).willReturn(book1);

        mockMvc.perform(get("/books/{ISBN}","ISBN1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is("Book1")))
                .andExpect(jsonPath("$.isbn",is("ISBN1")));
    }

    @Test
    public void givenBooks_whenGetBooksByTitle_thenReturnJsonBooks() throws Exception {

        given(bookRepository.findBooksByTitleContaining("Book")).willReturn(allBooks);

        mockMvc.perform(get("/books/title/{title}","Book"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title",is("Book1")))
                .andExpect(jsonPath("$[0].isbn",is("ISBN1")))
                .andExpect(jsonPath("$[1].title",is("Book2")))
                .andExpect(jsonPath("$[1].isbn",is("ISBN2")));
    }

}
