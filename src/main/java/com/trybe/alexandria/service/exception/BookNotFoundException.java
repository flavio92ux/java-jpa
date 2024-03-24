package com.trybe.alexandria.service.exception;

public class BookNotFoundException extends Exception {
  public BookNotFoundException() {
    super("Book not found.");
  }
}
