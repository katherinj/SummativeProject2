package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;

@Repository
@Component
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
