package com.trybe.alexandria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trybe.alexandria.controller.dto.AuthorCreationDto;
import com.trybe.alexandria.controller.dto.AuthorDto;
import com.trybe.alexandria.service.AuthorService;
import com.trybe.alexandria.service.exception.AuthorNotFoundException;

@RestController
@RequestMapping("/authors")
public class AuthorController {
  private final AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/{id}")
  public AuthorDto getAuthorById(@PathVariable Long id) throws AuthorNotFoundException {
    return AuthorDto.fromEntity(authorService.findById(id));
  }

  @GetMapping
  public List<AuthorDto> getAllAuthors() {
    return authorService.findAll().stream().map(AuthorDto::fromEntity).toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AuthorDto createAuthor(@RequestBody AuthorCreationDto authorDto) {
    return AuthorDto.fromEntity(authorService.create(authorDto.toEntity()));
  }

  @PutMapping("/{id}")
  public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorCreationDto authorDto) throws AuthorNotFoundException {
    return AuthorDto.fromEntity(authorService.update(id, authorDto.toEntity())); 
  }

  @DeleteMapping("/{id}")
  public AuthorDto deleteAuthor(@PathVariable Long id) throws AuthorNotFoundException {
    return AuthorDto.fromEntity(authorService.deleteById(id));
  }

}
