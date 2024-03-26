package com.trybe.alexandria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trybe.alexandria.controller.dto.PublisherCreationDto;
import com.trybe.alexandria.controller.dto.PublisherDto;
import com.trybe.alexandria.service.PublisherService;
import com.trybe.alexandria.service.exception.PublisherNotFoundException;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

  private final PublisherService publisherService;
  
  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping("/{id}")
  public PublisherDto getPublisherById(@PathVariable Long id) throws PublisherNotFoundException {
    return PublisherDto.fromEntity(publisherService.findById(id));
  }

  @GetMapping
  public List<PublisherDto> getAllPublishers() {
    return publisherService.findAll().stream().map(PublisherDto::fromEntity).toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PublisherDto createPublisher(@RequestBody PublisherCreationDto publisherCreationDto) {
    return PublisherDto.fromEntity(publisherService.create(publisherCreationDto.toEntity()));
  }

  @PutMapping("/{id}")
  public PublisherDto updatePublisher(@PathVariable Long id, @RequestBody PublisherCreationDto publisherCreationDto) throws PublisherNotFoundException {
    return PublisherDto.fromEntity(publisherService.update(id, publisherCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  public PublisherDto deletePublisher(@PathVariable Long id) throws PublisherNotFoundException {
    return PublisherDto.fromEntity(publisherService.deleteById(id));
  }
  
}
