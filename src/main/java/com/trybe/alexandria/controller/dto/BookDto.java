package com.trybe.alexandria.controller.dto;

import java.util.List;

import com.trybe.alexandria.entity.Book;

public record BookDto(Long id, String title, String genre, PublisherDto publisher, List<AuthorDto> authors) {
  public static BookDto fromEntity(Book book) {
    List<AuthorDto> authorDtos = book.getAuthors().stream().map(AuthorDto::fromEntity).toList();
    PublisherDto publisherDto = book.getPublisher() != null ? PublisherDto.fromEntity(book.getPublisher()) : null;

    return new BookDto(book.getId(), book.getTitle(), book.getGenre(), publisherDto, authorDtos);
  }
}
