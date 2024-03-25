package com.trybe.alexandria.controller.dto;

import com.trybe.alexandria.entity.Book;

public record BookDto(Long id, String title, String genre) {

  public static BookDto fromEntity(Book book) {
    return new BookDto(book.getId(), book.getTitle(), book.getGenre());
  }
}
