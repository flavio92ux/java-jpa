package com.trybe.alexandria.controller.dto;

import com.trybe.alexandria.entity.Publisher;

public record PublisherCreationDto(String name, String address) {

  public Publisher toEntity() {
    return new Publisher(name, address);
  }

}
