package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {
    @Autowired
    PublisherRepository repo;

    // A POST route that creates a new publisher
    @PostMapping("/publisher")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher Publisher) {
        return repo.save(Publisher);
    }

    // A PUT route that updates an existing publisher
    @PutMapping("/publisher")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher Publisher) {
        repo.save(Publisher);
    }

    // A DELETE route that deletes an existing publisher
    @DeleteMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) { repo.deleteById(id); }

    // A GET route that returns a specific publisher by id
    @GetMapping("/publisher/{id}")
    public Publisher getPublisherById(@PathVariable int id) {
        Optional<Publisher> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // A GET route that returns all publishers
    @GetMapping("/publisher")
    public List<Publisher> getAllPublishers() {
        return repo.findAll();
    }

}
