package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
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

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepository PublisherRepository;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of publishers for testing purposes
    private List<Publisher> publisherList;

    // testing POST route /publisher that creates a new publisher
    @Test
    public void shouldReturnNewPublisherOnPostRequest() throws Exception {
        // ARRANGE
        Publisher inputPublisher = new Publisher();
        inputPublisher.setName("Joe Smith LLC");
        inputPublisher.setStreet("123 Sunset Blvd");
        inputPublisher.setCity("Hollywood");
        inputPublisher.setState("California");
        inputPublisher.setPostalCode("01249");
        inputPublisher.setPhone("6099549392");
        inputPublisher.setEmail("joesmith@gmail.com");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(inputPublisher);

        Publisher outputPublisher = new Publisher();
        outputPublisher.setName("John Smith LLC");
        outputPublisher.setStreet("123 Sunset Blvd");
        outputPublisher.setCity("Hollywood");
        outputPublisher.setState("California");
        outputPublisher.setPostalCode("01249");
        outputPublisher.setPhone("6099549392");
        outputPublisher.setEmail("joesmith@gmail.com");

        // Convert Java Object to JSON
        String outputJson = mapper.writeValueAsString(outputPublisher);

        // ACT
        mockMvc.perform(
                        post("/publisher")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }

    // testing PUT /publisher route that updates an existing publisher
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        Publisher inputPublisher = new Publisher();
        inputPublisher.setName("Joe Smith LLC");
        inputPublisher.setStreet("123 Sunset Blvd");
        inputPublisher.setCity("Hollywood");
        inputPublisher.setState("California");
        inputPublisher.setPostalCode("01249");
        inputPublisher.setPhone("6099549392");
        inputPublisher.setEmail("joesmith@gmail.com");
        String inputJson = mapper.writeValueAsString(inputPublisher);

        mockMvc.perform(
                        put("/publisher")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // testing DELETE /publisher{id} route that deletes an existing publisher
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/publisher/5"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // testing GET /publisher{id} route that returns a specific publisher by id
    @Test
    public void shouldReturnPublisherById() throws Exception {
        Publisher inputPublisher = new Publisher();
        inputPublisher.setName("Joe Smith LLC");
        inputPublisher.setStreet("123 Sunset Blvd");
        inputPublisher.setCity("Hollywood");
        inputPublisher.setState("California");
        inputPublisher.setPostalCode("01249");
        inputPublisher.setPhone("6099549392");
        inputPublisher.setEmail("joesmith@gmail.com");
        inputPublisher.setPublisherId(2);

        String outputJson = mapper.writeValueAsString(inputPublisher);

        mockMvc.perform(get("/publisher/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // testing GET /publisher route that returns all publishers
    @Test
    public void shouldReturnAllPublishers() throws Exception {
        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(publisherList);

        // ACT
        mockMvc.perform(get("/publisher"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }
}
