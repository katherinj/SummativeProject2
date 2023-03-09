package com.company.bookstore.repository;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublisherRepositoryTest {
    @Autowired
    PublisherRepository publisherRepo;

    @Before
    public void setUp() throws Exception { publisherRepo.deleteAll(); }

    @Test
    public void shouldCreateNewPublisher() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");

        //Act...
        publisher = publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getPublisherId());
        assertEquals(publisher1.get(), publisher);
    }

    @Test
    public void shouldUpdatePublisher() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");

        //Act...
        publisher.setName("UPDATED");
        publisher = publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getPublisherId());
        assertEquals(publisher1.get(), publisher);
    }

    @Test
    public void shouldDeletePublisher() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");
        publisher = publisherRepo.save(publisher);

        //Act...
        publisherRepo.deleteById(publisher.getPublisherId());

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getPublisherId());
        assertFalse(publisher1.isPresent());
    }

    @Test
    public void shouldGetPublisherByID() {
        //Arrange...

        //Act...
        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");
        publisher = publisherRepo.save(publisher);

        Optional<Publisher> foundPublisher = publisherRepo.findById(publisher.getPublisherId());

        //Assert...
        assertEquals(foundPublisher.get(), publisher);
    }

    @Test
    public void shouldGetAllPublishers() {
        //Arrange...

        //Act...
        Publisher publisher = new Publisher();
        publisher.setName("Joe Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");
        publisherRepo.save(publisher);

        Publisher publisher1 = new Publisher();
        publisher.setName("John Smith LLC");
        publisher.setStreet("123 Sunset Blvd");
        publisher.setCity("Hollywood");
        publisher.setState("California");
        publisher.setPostalCode("01249");
        publisher.setPhone("6099549392");
        publisher.setEmail("joesmith@gmail.com");
        publisherRepo.save(publisher1);

        List<Publisher> publisherList = publisherRepo.findAll();

        //Assert...
        assertEquals(2, publisherList.size());
    }
}