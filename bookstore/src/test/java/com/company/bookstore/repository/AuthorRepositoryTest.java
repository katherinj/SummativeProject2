package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        // Act...

        // Assert...
    }

    @Test
    public void testReadAllAuthor() {
        // Arrange...

        // Act...

        // Assert...
    }

    @Test
    public void testReadAuthorById() {
        // Arrange...

        // Act...

        // Assert...
    }
    @Test
    public void testUpdateAuthor() {
        // Arrange...

        // Act...

        // Assert...
    }
    @Test
    public void testDeleteAuthor() {
        // Arrange...

        // Act...

        // Assert...
    }


}