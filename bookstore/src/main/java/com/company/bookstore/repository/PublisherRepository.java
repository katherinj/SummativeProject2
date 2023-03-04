package com.company.bookstore.repository;


import com.company.bookstore.model.Publisher;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>  {
}
