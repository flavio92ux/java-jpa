package com.trybe.alexandria.service.exception;

public class AuthorNotFoundException extends Exception {
  public AuthorNotFoundException() {
    super("Author not found.");
  }

}
