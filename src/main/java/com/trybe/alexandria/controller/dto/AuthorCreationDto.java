package com.trybe.alexandria.controller.dto;

import com.trybe.alexandria.entity.Author;

public record AuthorCreationDto(String name, String nationality) {

  public Author toEntity() {
    return new Author(name, nationality);
  }
}
