package com.trybe.alexandria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trybe.alexandria.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  
}
