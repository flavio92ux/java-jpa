package com.trybe.alexandria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trybe.alexandria.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
