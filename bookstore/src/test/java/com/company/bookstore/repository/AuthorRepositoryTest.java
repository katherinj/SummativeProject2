package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;
    @Before
    public void setUp() throws Exception {
        authorRepository.deleteAll();
    }
    @Test
    public void testCreateAuthor() {
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
        authorRepository.save(author);
        // Act...
        Optional<Author> author1 = authorRepository.findById(author.getAuthorId());
        // Assert...
        assertEquals(author1, author);
    }

    @Test
    public void testReadAllAuthor() {
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
        authorRepository.save(author);

        Author author1 = new Author();
        author1.setEmail("mark@gmail.com");
        author1.setPhone("111-222-444");
        author1.setFirstName("Mark");
        author1.setLastName("Zhou");
        author1.setStreet("Hollywood");
        author1.setPostalCode("11100");
        author1.setState("CA");
        author1.setCity("Los Angeles");
        authorRepository.save(author1);


        // Act...
        List<Author> authors = authorRepository.findAll();

        // Assert...
        assertEquals(authors.size(), 2);
    }

    @Test
    public void testUpdateAuthor() {
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
        authorRepository.save(author);
        // Act...
        author.setCity("Charlottesville");
        author.setState("VA");
        author.setPostalCode("22903");
        author.setStreet("Fontaine Ave");
        // Assert...
        Optional<Author> author1 = authorRepository.findById(author.getAuthorId());
        assertEquals(author1, author);
    }
    @Test
    public void testDeleteAuthor() {
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
        authorRepository.save(author);

        // Act...
        authorRepository.deleteById(author.getAuthorId());
        // Assert...
        Optional<Author> author1 = authorRepository.findById(author.getAuthorId());
        assertFalse(author1.isPresent());
    }


}