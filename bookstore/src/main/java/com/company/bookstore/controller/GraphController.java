package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    PublisherRepository publisherRepository;

    @QueryMapping
    public List<Publisher> publishers() {
        return  publisherRepository.findAll();
    }

    // find Publisher by Id (Including books for the publisher and authors for the books)
    @QueryMapping
    public Publisher findPublisherById(@Argument Integer id) {
        Optional<Publisher> returnVal = publisherRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }


    // find Author by Id (Including books by the author)
    // find Book by Id (Including the author and publisher of the book)


}
