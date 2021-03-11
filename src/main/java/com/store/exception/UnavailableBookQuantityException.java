package com.store.exception;

public class UnavailableBookQuantityException extends BaseException {

    private static final String ID = "UNAVAILABLE_BOOK_QUANTITY";

    public UnavailableBookQuantityException(String message) {
        super(ID, message, 400);
    }
}
