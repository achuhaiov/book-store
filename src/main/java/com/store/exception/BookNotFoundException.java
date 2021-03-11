package com.store.exception;

public class BookNotFoundException extends BaseException {

    private static final String ID = "BOOK_NOT_FOUND";

    public BookNotFoundException(String message) {
        super(ID, message, 404);
    }
}
