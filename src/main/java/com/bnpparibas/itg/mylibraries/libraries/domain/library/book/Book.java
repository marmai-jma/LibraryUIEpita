package com.bnpparibas.itg.mylibraries.libraries.domain.library.book;

import com.bnpparibas.itg.mylibraries.libraries.domain.ddd.DDD;

@DDD.Entity
public class Book {

    private Long id;

    private String title;

    private String author;

    private int numberOfPage;

    private LiteraryGenre literaryGenre;

    private Book() {}

    public Book(Long id, String title, String author, int numberOfPage, LiteraryGenre literaryGenre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPage = numberOfPage;
        this.literaryGenre = literaryGenre;
    }

    public void update(Book bookWithNewInformation) {
        this.title = bookWithNewInformation.getTitle();
        this.author = bookWithNewInformation.getAuthor();
        this.numberOfPage = bookWithNewInformation.getNumberOfPage();
        this.literaryGenre = bookWithNewInformation.getLiteraryGenre();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public LiteraryGenre getLiteraryGenre() {
        return literaryGenre;
    }

    @Override public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        Book that = this.getClass().cast(obj);

        return that.id.equals(this.id);
    }

    @Override public int hashCode() {
        return this.id.hashCode();
    }

    @Override public String toString() {
        return String.format("%s{id:%s)", this.getClass().getSimpleName(), id);
    }
}