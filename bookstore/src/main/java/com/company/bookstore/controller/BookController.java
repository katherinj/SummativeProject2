package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @GetMapping(value = "/book", params = "authorId")
    public Book searchBookByAuthorId(@RequestParam Integer authorId) {
        Optional<Book> book= bookRepository.findById(authorId);
        if (book.isPresent()) return book.get();
        return null;
    }
    @GetMapping(value = "/book")
    public List<Book> readAllBook() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }
    @GetMapping("/book/{id}")
    public Book readBookById(@PathVariable Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) return book.get();
        return null;
    }
    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return book = bookRepository.save(book);
    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }

    @PutMapping("/book")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Book updateBook(@RequestBody Book book) {
        return book = bookRepository.save(book);
    }
}
