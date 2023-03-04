package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;
    @PostMapping(path = "/")
    public void createAuthor() {

    }
    @PutMapping(path = "/")
    public void updateAuthor() {

    }

    @DeleteMapping(path = "")
    public void deleteAuthor(@PathVariable Integer id) {
        authorRepository.deleteById(id);
    }
    @GetMapping("/")
    public List<Author> readAllBook() {
        List<Author> authorList = authorRepository.findAll();
        return authorList;
    }

    @GetMapping()
    public Author readAuthorById(@PathVariable Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) return author.get();
        return null;
    }
}
