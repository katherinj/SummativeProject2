package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Before
    public void setUp() throws Exception {
        bookRepository.deleteAll();
    }
    @Test
    public void testCreateBook() {
        // Arrange...
        Author author = new Author();
        author.setEmail("sking@gmail.com");
        author.setPhone("111-222-3333");
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setPostalCode("11100");
        author.setState("CA");
        author.setCity("Los Angeles");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(author.getAuthorId());
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPublishDate(Date.valueOf("2015-08-01"));

        // Act...
        book = bookRepository.save(book);
        // Assert...
        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertEquals(book1.get(), book);
    }

    @Test
    public void testSearchBookByAuthorId() {
        // Arrange...
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(Date.valueOf("2015-08-01"));

        book = bookRepository.save(book);

        Book book1 = new Book();
        book1.setIsbn("0783161484101");
        book1.setAuthorId(0);
        book1.setPrice(30.00);
        book1.setTitle("A boring book");
        book1.setPublisherId(1);
        book1.setPublishDate(Date.valueOf("2015-08-01"));
        book1 = bookRepository.save(book1);
        // Act...
        Set<Book> bookList = bookRepository.findByAuthorId(1);
        // Assert...
        assertEquals(1, bookList.size());
    }
    @Test
    public void testReadAllBook() {
        // Arrange...
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book = bookRepository.save(book);

        Book book1 = new Book();
        book1.setIsbn("0783161484101");
        book1.setAuthorId(0);
        book1.setPrice(30.00);
        book1.setTitle("A boring book");
        book1.setPublisherId(1);
        book1.setPublishDate(Date.valueOf("2015-08-01"));
        book1 = bookRepository.save(book1);
        // Act...
        List<Book> bookList = bookRepository.findAll();
        assertEquals(1, bookList.size());
    }

    @Test
    public void testReadBookById() {
        // Arrange...
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book = bookRepository.save(book);
        // Act...
        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertEquals(book1.get(), book);
    }

    @Test
    public void deleteBook() {
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book = bookRepository.save(book);

        bookRepository.deleteAll();
        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertFalse(book1.isPresent());
    }

    @Test
    public void updateBook() {
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book = bookRepository.save(book);

        book.setTitle("Updated!");
        bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertEquals(book1.get(), book);
    }
}