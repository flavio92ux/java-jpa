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

import com.trybe.alexandria.controller.dto.BookCreationDto;
import com.trybe.alexandria.controller.dto.BookDetailCreationDto;
import com.trybe.alexandria.controller.dto.BookDetailDto;
import com.trybe.alexandria.controller.dto.BookDto;
import com.trybe.alexandria.service.BookService;
import com.trybe.alexandria.service.exception.AuthorNotFoundException;
import com.trybe.alexandria.service.exception.BookDetailNotFoundException;
import com.trybe.alexandria.service.exception.BookNotFoundException;
import com.trybe.alexandria.service.exception.PublisherNotFoundException;

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

  @PutMapping("/{id}")
  public BookDto updateBook(@PathVariable Long id, @RequestBody BookCreationDto bookCreationDto)
      throws BookNotFoundException {
    return BookDto.fromEntity(bookService.update(id, bookCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  public BookDto deleteBook(@PathVariable Long id) throws BookNotFoundException {
    return BookDto.fromEntity(bookService.deleteById(id));
  }

  @PostMapping("/{bookId}/details")
  @ResponseStatus(HttpStatus.CREATED)
  public BookDetailDto createBookDetail(@PathVariable Long bookId,
      @RequestBody BookDetailCreationDto bookDetailCreationDto) throws BookNotFoundException {
    return BookDetailDto.fromEntity(bookService.createBookDetail(bookId, bookDetailCreationDto.toEntity()));
  }

  @GetMapping("/{bookId}/details")
  public BookDetailDto getBookDetail(@PathVariable Long bookId)
      throws BookNotFoundException, BookDetailNotFoundException {
    return BookDetailDto.fromEntity(bookService.getBookDetail(bookId));
  }

  @PutMapping("/{bookId}/details")
  public BookDetailDto updateBookDetail(@PathVariable Long bookId,
      @RequestBody BookDetailCreationDto bookDetailCreationDto)
      throws BookNotFoundException, BookDetailNotFoundException {
    return BookDetailDto.fromEntity(bookService.updateBookDetail(bookId, bookDetailCreationDto.toEntity()));
  }

  @DeleteMapping("/{bookId}/details")
  public BookDetailDto deleteBookDetail(@PathVariable Long bookId)
      throws BookNotFoundException, BookDetailNotFoundException {
    return BookDetailDto.fromEntity(bookService.deleteBookDetail(bookId));
  }

  @PutMapping("/{bookId}/publisher/{publisherId}")
  public BookDto setBookPublisher(@PathVariable Long bookId, @PathVariable Long publisherId)
      throws BookNotFoundException, PublisherNotFoundException {
    return BookDto.fromEntity(bookService.setBookPublisher(bookId, publisherId));
  }

  @DeleteMapping("/{bookId}/publisher")
  public BookDto removeBookPublisher(@PathVariable Long bookId) throws BookNotFoundException {
    return BookDto.fromEntity(bookService.removeBookPublisher(bookId));
  }

  @PutMapping("/{bookId}/authors/{authorId}")
  public BookDto addBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId)
      throws BookNotFoundException, AuthorNotFoundException {
    return BookDto.fromEntity(bookService.addBookAuthor(bookId, authorId));
  }

  @DeleteMapping("/{bookId}/authors/{authorId}")
  public BookDto removeBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId) throws BookNotFoundException, AuthorNotFoundException {
    return BookDto.fromEntity(bookService.removeBookAuthor(bookId, authorId));
  }
}
