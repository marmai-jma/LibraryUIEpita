package com.bnpparibas.itg.mylibraries.libraries.domain.library;

import com.bnpparibas.itg.mylibraries.libraries.domain.ddd.DDD;
import com.bnpparibas.itg.mylibraries.libraries.domain.exception.ErrorCodes;
import com.bnpparibas.itg.mylibraries.libraries.domain.exception.MyAppBookException;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.Book;

import javax.persistence.Embedded;
import java.util.*;

@DDD.Entity
public class Library {

    private String id;

    private String label;

    private Type type;

    private Address address;

    private Director director;

    private List<Book> books = new ArrayList<>();

    public Library() {}

    public Library(String id, String label, Type type, Address address, Director director, List<Book> books) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.address = address;
        this.director = director;
        this.books = books;
        validate();
    }

    public void update(Library libraryWithNewInformation) {
        this.label = libraryWithNewInformation.getLabel();
        this.type = libraryWithNewInformation.getType();
        this.address = libraryWithNewInformation.getAddress();
        this.director = libraryWithNewInformation.getDirector();
        validate();
    }

    private void validate() {
        Set<String> errors = new HashSet<>();

        this.director.validate(errors);

        if(!errors.isEmpty()) {
            throw new MyAppBookException(errors);
        }
    }

    public void addBook(Book book) {
        this.getBooks().add(book);
    }

    public void updateBook(Long bookId, Book bookWithNewInformation) {
        Book book = searchBook(bookId);
        book.update(bookWithNewInformation);
    }

    public void removeBook(Long bookId) {
        Book book = searchBook(bookId);
        this.books.remove(book);
    }

    private Book searchBook(Long bookId) {
        Book book = this.books.stream().filter(l -> l.getId().equals(bookId)).findFirst().orElseThrow(() -> new MyAppBookException(ErrorCodes.BOOK_NOT_FOUND));
        return book;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Type getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public Director getDirector() {
        return director;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        Library that = this.getClass().cast(obj);

        return that.id.equals(this.id);
    }

    @Override public int hashCode() {
        return this.id.hashCode();
    }

    @Override public String toString() {
        return String.format("%s{id:%s)", this.getClass().getSimpleName(), id);
    }
}
