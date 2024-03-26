package com.trybe.alexandria.controller.dto;

import com.trybe.alexandria.entity.Publisher;

public record PublisherDto(Long id, String name, String address) {
  public static PublisherDto fromEntity(Publisher publisher) {
    return new PublisherDto(publisher.getId(), publisher.getName(), publisher.getAddress());
  }
}
