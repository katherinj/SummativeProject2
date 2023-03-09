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
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepo;

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
        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(author.getAuthorId());
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getId());
        book.setPublishDate(Date.valueOf("2015-08-01"));
        // Act...
        book = bookRepository.save(book);
        // Assert...
        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertEquals(book1.get().toString(), book.toString());
    }

    @Test
    public void testSearchBookByAuthorId() {
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

        Author author2 = new Author();
        author2.setEmail("sking@gmail.com");
        author2.setPhone("111-222-3333");
        author2.setFirstName("Stephen");
        author2.setLastName("King");
        author2.setStreet("Hollywood");
        author2.setPostalCode("11100");
        author2.setState("CA");
        author2.setCity("Los Angeles");
        author2 = authorRepository.save(author2);

        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");
        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(author.getAuthorId());
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getId());
        book.setPublishDate(Date.valueOf("2015-08-01"));

        book = bookRepository.save(book);

        Book book1 = new Book();
        book1.setIsbn("0783161484101");
        book1.setAuthorId(author2.getAuthorId());
        book1.setPrice(30.00);
        book1.setTitle("A boring book");
        book1.setPublisherId(publisher.getId());
        book1.setPublishDate(Date.valueOf("2015-08-01"));
        book1 = bookRepository.save(book1);
        // Act...
        Set<Book> bookList = bookRepository.findByAuthorId(author.getAuthorId());
        // Assert...
        assertEquals(1, bookList.size());
    }
    @Test
    public void testReadAllBook() {
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
        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(author.getAuthorId());
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getId());
        book = bookRepository.save(book);

        Book book1 = new Book();
        book1.setIsbn("0783161484101");
        book1.setAuthorId(author.getAuthorId());
        book1.setPrice(30.00);
        book1.setTitle("A boring book");
        book1.setPublisherId(publisher.getId());
        book1.setPublishDate(Date.valueOf("2015-08-01"));
        book1 = bookRepository.save(book1);

        // Act...
        List<Book> bookList = bookRepository.findAll();
        assertEquals(2, bookList.size());
    }

    @Test
    public void testReadBookById() {
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
        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(author.getAuthorId());
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getId());
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book = bookRepository.save(book);
        // Act...
        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertEquals(book1.get().toString(), book.toString());
    }

    @Test
    public void deleteBook() {

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
        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(author.getAuthorId());
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getId());
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book = bookRepository.save(book);

        bookRepository.deleteAll();
        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertFalse(book1.isPresent());
    }

    @Test
    public void updateBook() {

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
        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(author.getAuthorId());
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getId());
        book.setPublishDate(Date.valueOf("2015-08-01"));
        book = bookRepository.save(book);

        book.setTitle("Updated!");
        bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getBookId());
        assertEquals(book1.get().toString(), book.toString());
    }
}