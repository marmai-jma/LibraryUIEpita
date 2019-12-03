package com.bnpparibas.itg.mylibraries.libraries.infrastructure;

import com.bnpparibas.itg.mylibraries.libraries.domain.library.Address;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Director;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Library;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Type;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.Book;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "LIBRARY")
public class LibraryJPA {

    @Id
    //@GeneratedValue()
    @Column(name = "ID")
    private String id;

    @Column(name = "LABEL")
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Type type;

    @Column(name = "ADDRESS_NUMBER")
    private int addressNumber;

    @Column(name = "ADDRESS_STREET")
    private String addressStreet;

    @Column(name = "ADDRESS_POSTALCODE")
    private int addressPostalCode;

    @Column(name = "ADDRESS_CITY")
    private String addressCity;

    @Column(name = "DIRECTOR_SURNAME")
    private String directorSurname;

    @Column(name = "DIRECTOR_NAME")
    private String directorName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="LIBRARY_ID", referencedColumnName = "ID")
    private List<BookJPA> books;

    private LibraryJPA() {}

    public LibraryJPA(Library library) {
        this.id = library.getId();
        this.label = library.getLabel();
        this.type = library.getType();
        this.addressNumber = library.getAddress().getNumber();
        this.addressStreet = library.getAddress().getStreet();
        this.addressPostalCode = library.getAddress().getPostalCode();
        this.addressCity = library.getAddress().getCity();
        this.directorSurname = library.getDirector().getSurname();
        this.directorName = library.getDirector().getName();
        this.books = library.getBooks().stream().map(BookJPA::new).collect(Collectors.toList());
    }

    public Library toLibrary() {
        Address address = new Address(addressNumber, addressStreet, addressPostalCode, addressCity);

        Director director = new Director(directorSurname, directorName);

        List<Book> bookList = books.stream().map(b -> new Book(b.getId(), b.getTitle(), b.getAuthor(), b.getNumberOfPage(), b.getLiteraryGenre())).collect(Collectors.toList());

        return new Library(id, this.label, this.type, address, director, bookList);
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

    public int getAddressNumber() {
        return addressNumber;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public int getAddressPostalCode() {
        return addressPostalCode;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getDirectorSurname() {
        return directorSurname;
    }

    public String getDirectorName() {
        return directorName;
    }

    public List<BookJPA> getBooks() {
        return books;
    }
}
