package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphControllerTest {
    PublisherRepository publisherRepository;
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    GraphController controller;

    @Before
    public void setUp() {
        setAuthorRepositoryMock();
        setUpBookRepositoryMock();
        setUpPublisherRepositoryMock();
    }
    @Test
    public void testGetAllPublishers() {
        List<Publisher> publisherList = controller.publishers();

        assertEquals(1, publisherList.size());
    }

    @Test
    public void testFindPublisherById() {
        Publisher publisher = controller.findPublisherById(1);

        Publisher expectPublisher = new Publisher();
        expectPublisher.setName("Joe Smith LLC");
        expectPublisher.setStreet("123 Sunset Blvd");
        expectPublisher.setCity("Hollywood");
        expectPublisher.setState("California");
        expectPublisher.setPostalCode("01249");
        expectPublisher.setPhone("6099549392");
        expectPublisher.setEmail("joesmith@gmail.com");
        expectPublisher.setId(1);

        assertEquals(expectPublisher, publisher);
    }

    @Test
    public void testFindAuthorById() {
        Author author = controller.findAuthorById(1);

        Author expectedAuthor = new Author();
        expectedAuthor.setEmail("sking@gmail.com");
        expectedAuthor.setPhone("111-222-3333");
        expectedAuthor.setFirstName("Stephen");
        expectedAuthor.setLastName("King");
        expectedAuthor.setStreet("Hollywood");
        expectedAuthor.setPostalCode("11100");
        expectedAuthor.setState("CA");
        expectedAuthor.setCity("Los Angeles");
        expectedAuthor.setAuthorId(1);

        assertEquals(expectedAuthor, author);
    }

    @Test
    public void testFindBookById() {
        Book book = controller.findBookById(1);
        Book expectBook = new Book();
        expectBook.setIsbn("9783161484100");
        expectBook.setAuthorId(1);
        expectBook.setPrice(29.99);
        expectBook.setTitle("An interesting book");
        expectBook.setPublisherId(1);
        expectBook.setPublishDate(new Date("2015-08-01"));
        expectBook.setBookId(1);

        assertEquals(expectBook, book);
    }



    private void setUpPublisherRepositoryMock() {
        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");
        publisher.setId(1);
        List<Publisher> list = new ArrayList<>();
        list.add(publisher);

        publisherRepository = mock(PublisherRepository.class);
        doReturn(Optional.of(publisher)).when(publisherRepository).findById(1);
        doReturn(list).when(publisherRepository).findAll();
    }
    private void setUpBookRepositoryMock() {
        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setAuthorId(1);
        book.setPrice(29.99);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPublishDate(new Date("2015-08-01"));
        book.setBookId(1);

        bookRepository = mock(BookRepository.class);
        doReturn(Optional.of(book)).when(bookRepository).findById(1);
    }
    private void setAuthorRepositoryMock() {
        Author author = new Author();
        author.setEmail("sking@gmail.com");
        author.setPhone("111-222-3333");
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setPostalCode("11100");
        author.setState("CA");
        author.setCity("Los Angeles");
        author.setAuthorId(1);

        authorRepository = mock(AuthorRepository.class);
        doReturn(Optional.of(author)).when(authorRepository).findById(1);
    }
}