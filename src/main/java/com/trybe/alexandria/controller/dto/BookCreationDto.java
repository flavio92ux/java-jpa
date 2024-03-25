package com.trybe.alexandria.controller.dto;

import com.trybe.alexandria.entity.Book;

public record BookCreationDto(String title, String genre) {
  public Book toEntity() {
    return new Book(title, genre);
  }

}
