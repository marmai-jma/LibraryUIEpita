package com.bnpparibas.itg.mylibraries.libraries.infrastructure;

import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.Book;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.LiteraryGenre;
import javax.persistence.*;

@Entity(name = "BOOK")
public class BookJPA {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "NUMBER_OF_PAGE")
    private int numberOfPage;

    @Enumerated(EnumType.STRING)
    @Column(name = "LITERARY_GENRE")
    private LiteraryGenre literaryGenre;

    private BookJPA() {}

    public BookJPA(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.numberOfPage = book.getNumberOfPage();
        this.literaryGenre = book.getLiteraryGenre();
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
}