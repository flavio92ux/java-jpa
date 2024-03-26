package com.trybe.alexandria.service.exception;

public class BookDetailNotFoundException extends NotFoundException {
  public BookDetailNotFoundException() {
    super("Book detail not found.");
  }
}
