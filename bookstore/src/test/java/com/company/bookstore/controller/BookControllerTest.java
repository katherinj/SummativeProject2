package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    // ObjectMapper used to convert Java objects to JSON and vice versa
    @MockBean
    BookRepository bookRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        // Standard set up method for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
    }
    // Testing GET /book
    @Test
    public void testReadAllBooks() throws Exception {
        // ARRANGE
        // Convert Java object to JSON
        // ACT
        mockMvc.perform(get("/book"))        // Perform the GET request
                .andDo(print())              // Print results to console
                .andExpect(status().isOk());        // ASSERT (status code is 200)
    }
    // Testing GET book/{id}
    @Test
    public void testReadBookById() throws Exception {
        mockMvc.perform(get("/book/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing POST /book
    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(Date.valueOf("2015-08-01"));

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(book);

        Book outputBook = new Book();
        outputBook.setIsbn("9783161484100");
        outputBook.setAuthorId(1);
        outputBook.setPrice(29.99);
        outputBook.setTitle("An interesting book");
        outputBook.setPublisherId(1);
        outputBook.setPublishDate(Date.valueOf("2015-08-01"));
        outputBook.setBookId(2);

        String outputJson = mapper.writeValueAsString(outputBook);

        // ACT
        mockMvc.perform(
                        post("/book")              // Perform the POST request
                                .content(inputJson)             // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                // Print results to console
                .andExpect(status().isCreated());        // ASSERT (status code is 201)
    }

    // Testing PUT /book
    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(Date.valueOf("2015-08-01"));

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(book);

        mockMvc.perform(put("/book")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /book/{id}
    @Test
    public void testDeleteBookById() throws Exception {
        mockMvc.perform(delete("/book/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}