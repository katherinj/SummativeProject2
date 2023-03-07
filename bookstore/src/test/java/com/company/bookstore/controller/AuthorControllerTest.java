package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)

public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AuthorRepository authorRepository;
    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testReadAllAuthor() throws Exception {
        mockMvc.perform(get("/author"))        // Perform the GET request
                .andDo(print())              // Print results to console
                .andExpect(status().isOk());        // ASSERT (status code is 200)
    }
    @Test
    public void testReadAuthorById() throws Exception {
        Author author = new Author();
        author.setEmail("sking@gmail.com");
        author.setPhone("111-222-3333");
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setPostalCode("11100");
        author.setState("CA");
        author.setCity("Los Angeles");
        author.setAuthorId(2);
        String outputJson = mapper.writeValueAsString(author);
        mockMvc.perform(get("/author/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAuthor() throws Exception {
        Author author = new Author();
        author.setEmail("sking@gmail.com");
        author.setPhone("111-222-3333");
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setPostalCode("11100");
        author.setState("CA");
        author.setCity("Los Angeles");
        author.setAuthorId(2);
        String inputJson = mapper.writeValueAsString(author);

        mockMvc.perform(
                        post("/author")              // Perform the POST request
                                .content(inputJson)             // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                // Print results to console
                .andExpect(status().isCreated());        // ASSERT (status code is 201)
    }

    @Test
    public void testUpdateAuthor() throws Exception {
        Author author = new Author();
        author.setEmail("sking@gmail.com");
        author.setPhone("111-222-3333");
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setPostalCode("11100");
        author.setState("CA");
        author.setCity("Los Angeles");
        author.setAuthorId(2);
        String inputJson = mapper.writeValueAsString(author);


        mockMvc.perform(put("/author")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAuthorById() throws Exception {
        mockMvc.perform(delete("/author/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}