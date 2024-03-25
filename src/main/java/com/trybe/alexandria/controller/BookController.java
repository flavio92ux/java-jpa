package com.trybe.alexandria.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.trybe.alexandria.controller.dto.BookCreationDto;
import com.trybe.alexandria.controller.dto.BookDto;
import com.trybe.alexandria.service.BookService;
import com.trybe.alexandria.service.exception.BookNotFoundException;

@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public BookDto getBookById(@PathVariable Long id) throws BookNotFoundException {
    return BookDto.fromEntity(bookService.findById(id));
  }

  @GetMapping
  public List<BookDto> getAllBooks() {
    return bookService.findAll().stream().map(BookDto::fromEntity).toList();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookDto createBook(@RequestBody BookCreationDto bookCreationDto) {
    return BookDto.fromEntity(bookService.create(bookCreationDto.toEntity()));
  }

  @PutMapping("/{id}"	)
  public BookDto updateBook(@PathVariable Long id, @RequestBody BookCreationDto bookCreationDto) throws BookNotFoundException {
    return BookDto.fromEntity(bookService.update(id, bookCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  public BookDto deleteBook(@PathVariable Long id) throws BookNotFoundException {
    return BookDto.fromEntity(bookService.deleteById(id));
  }
}
