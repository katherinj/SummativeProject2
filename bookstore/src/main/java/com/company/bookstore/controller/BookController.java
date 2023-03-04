package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @GetMapping()
    public Book searchBookByAuthorId(@PathVariable Integer authorId) {
        Optional<Book> book= bookRepository.findById(authorId);
        if (book.isPresent()) return book.get();
        return null;
    }
    @GetMapping()
    public List<Book> readAllBook() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }
    @GetMapping()
    public Book readBookById(@PathVariable Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) return book.get();
        return null;
    }
    @PostMapping()
    public void createBook(@RequestParam Book book) {
        bookRepository.save(book);
    }
}
