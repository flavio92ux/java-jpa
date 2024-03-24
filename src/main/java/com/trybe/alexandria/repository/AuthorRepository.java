package com.trybe.alexandria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trybe.alexandria.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
