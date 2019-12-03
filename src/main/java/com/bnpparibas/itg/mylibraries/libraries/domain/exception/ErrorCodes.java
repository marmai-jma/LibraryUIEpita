package com.bnpparibas.itg.mylibraries.libraries.domain.exception;

public final class ErrorCodes {
    private ErrorCodes() {}

    public static final String NOT_FOUND = "ERR_0001";
    public static final String LIBRARY_NOT_FOUND = NOT_FOUND + ".1";
    public static final String BOOK_NOT_FOUND = NOT_FOUND + ".2";

    public static final String LIBRARY_MUST_HAVE_A_DIRECTOR = "ERR_0002";
    public static final String LIBRARY_MUST_HAVE_AN_ADDRESS = "ERR_0003";
    public static final String DIRECTOR_MUST_HAVE_A_SURNAME = "ERR_0004";
    public static final String DIRECTOR_MUST_HAVE_A_NAME = "ERR_0005";
}