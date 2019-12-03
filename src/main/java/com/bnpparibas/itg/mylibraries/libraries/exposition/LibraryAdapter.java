package com.bnpparibas.itg.mylibraries.libraries.exposition;

import com.bnpparibas.itg.mylibraries.libraries.domain.library.Address;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Director;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Library;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class LibraryAdapter {

    private LibraryAdapter() {}

    public static Library transformToLibrary(LibraryDTO libraryDTO) {
        Address address = new Address(
                libraryDTO.addressDTO.number,
                libraryDTO.addressDTO.street,
                libraryDTO.addressDTO.postalCode,
                libraryDTO.addressDTO.city
        );

        Director director = new Director(libraryDTO.directorDTO.surname, libraryDTO.directorDTO.name);

        String id = (libraryDTO.id == null || libraryDTO.id.trim().equals(""))? UUID.randomUUID().toString() : libraryDTO.id;

        return new Library(id, libraryDTO.label, libraryDTO.type, address, director, transformToBookList(libraryDTO.bookDTOList));
    }

    public static List<Book> transformToBookList(List<LibraryDTO.BookDTO> bookDTO) {
        if(bookDTO == null) {
            return new ArrayList<>();
        }

        return bookDTO.stream().map(LibraryAdapter::transformToBook).collect(Collectors.toList());
    }

    public static Book transformToBook(LibraryDTO.BookDTO bookDTO) {
        return new Book(null, bookDTO.title, bookDTO.author, bookDTO.numberOfPage, bookDTO.literaryGenre);
    }

    public static List<LibraryDTO> adaptToLibraryDTOList(List<Library> libraries) {
        return libraries.stream().map(LibraryAdapter::adaptToLibraryDTO).collect(Collectors.toList());
    }

    public static LibraryDTO adaptToLibraryDTO(Library library) {
        return new LibraryDTO(library.getId(),
                library.getLabel(),
                library.getType(),
            new LibraryDTO.AddressDTO(library.getAddress().getNumber(),
                library.getAddress().getStreet(),
                library.getAddress().getPostalCode(),
                library.getAddress().getCity()
            ),
            new LibraryDTO.DirectorDTO(
                library.getDirector().getSurname(),
                library.getDirector().getName()
            ),
            LibraryAdapter.adaptToBookListDTO(library.getBooks())
        );
    }

    public static List<LibraryDTO.BookDTO> adaptToBookListDTO(List<Book> books) {
        return books.stream().map(LibraryAdapter::adaptToBookDTO).collect(Collectors.toList());
    }

    public static LibraryDTO.BookDTO adaptToBookDTO(Book book) {
        return new LibraryDTO.BookDTO(
            book.getTitle(),
            book.getAuthor(),
            book.getNumberOfPage(),
            book.getLiteraryGenre()
        );
    }

}
