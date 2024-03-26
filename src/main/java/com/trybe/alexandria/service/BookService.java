package com.trybe.alexandria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.trybe.alexandria.controller.dto.BookDto;
import com.trybe.alexandria.entity.Author;
import com.trybe.alexandria.entity.Book;
import com.trybe.alexandria.entity.BookDetail;
import com.trybe.alexandria.entity.Publisher;
import com.trybe.alexandria.repository.BookDetailRepository;
import com.trybe.alexandria.repository.BookRepository;
import com.trybe.alexandria.service.exception.AuthorNotFoundException;
import com.trybe.alexandria.service.exception.BookDetailNotFoundException;
import com.trybe.alexandria.service.exception.BookNotFoundException;
import com.trybe.alexandria.service.exception.PublisherNotFoundException;

@Service
public class BookService {
  private final BookRepository bookRepository;
  private final BookDetailRepository bookDetailRepository;
  private final PublisherService publisherService;
  private final AuthorService authorService;

  @Autowired
  public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository,
      PublisherService publisherService, AuthorService authorService) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
    this.publisherService = publisherService;
    this.authorService = authorService;
  }

  public Book findById(Long id) throws BookNotFoundException {
    return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book create(Book book) {
    return bookRepository.save(book);
  }

  public Book update(Long id, Book book) throws BookNotFoundException {
    Book bookFromDb = findById(id);

    bookFromDb.setTitle(book.getTitle());
    bookFromDb.setGenre(book.getGenre());

    return bookRepository.save(bookFromDb);
  }

  public Book deleteById(Long id) throws BookNotFoundException {
    Book book = findById(id);

    bookRepository.deleteById(id);

    return book;
  }

  public BookDetail createBookDetail(Long id, BookDetail bookDetail) throws BookNotFoundException {
    Book book = findById(id);

    bookDetail.setBook(book);

    return bookDetailRepository.save(bookDetail);
  }

  public BookDetail getBookDetail(Long id) throws BookNotFoundException, BookDetailNotFoundException {
    Book book = findById(id);

    BookDetail bookDetail = book.getBookDetail();

    if (bookDetail == null) {
      throw new BookDetailNotFoundException();
    }

    return bookDetail;
  }

  public BookDetail updateBookDetail(Long bookId, BookDetail bookDetail)
      throws BookNotFoundException, BookDetailNotFoundException {
    BookDetail bookDetailFromDb = getBookDetail(bookId);

    bookDetailFromDb.setSummary(bookDetail.getSummary());
    bookDetailFromDb.setPageCount(bookDetail.getPageCount());
    bookDetailFromDb.setYear(bookDetail.getYear());
    bookDetailFromDb.setIsbn(bookDetail.getIsbn());

    return bookDetailRepository.save(bookDetailFromDb);
  }

  public BookDetail deleteBookDetail(Long bookId) throws BookNotFoundException, BookDetailNotFoundException {
    Book book = findById(bookId);
    BookDetail bookDetail = getBookDetail(bookId);

    book.setBookDetail(null);
    bookDetail.setBook(null);

    bookDetailRepository.delete(bookDetail);

    return bookDetail;
  }

  public Book setBookPublisher(Long bookId, Long publisherId) throws BookNotFoundException, PublisherNotFoundException {
    Book book = findById(bookId);

    Publisher publisher = publisherService.findById(publisherId);

    book.setPublisher(publisher);

    return bookRepository.save(book);
  }

  public Book removeBookPublisher(Long bookId) throws BookNotFoundException {
    Book book = findById(bookId);

    book.setPublisher(null);

    return bookRepository.save(book);
  }

  public Book addBookAuthor(Long bookId, Long authorId) throws BookNotFoundException, AuthorNotFoundException {
    Book book = findById(bookId);
    Author author = authorService.findById(authorId);

    book.getAuthors().add(author);

    return bookRepository.save(book);
  }

  public Book removeBookAuthor(Long bookId, Long authorId) throws BookNotFoundException, AuthorNotFoundException {
    Book book = findById(bookId);
    Author author = authorService.findById(authorId);

    book.getAuthors().remove(author);

    return bookRepository.save(book);
  }
}
