package com.trybe.alexandria.controller.dto;

import com.trybe.alexandria.entity.BookDetail;

public record BookDetailCreationDto(String summary, Integer pageCount, String year, String isbn) {

  public BookDetail toEntity() {
    return new BookDetail(summary, pageCount, year, isbn);
  }
}
