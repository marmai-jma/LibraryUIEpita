package com.bnpparibas.itg.mylibraries.libraries.exposition;

import com.bnpparibas.itg.mylibraries.libraries.application.LibraryService;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Type;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LibraryResource {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(method = RequestMethod.POST, path = {"/libraries"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createLibrary(@Valid @RequestBody LibraryDTO libraryDTO) {
         this.libraryService.create(LibraryAdapter.transformToLibrary(libraryDTO));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/libraries/{libraryId}"})
    public LibraryDTO detailLibrary(@PathVariable("libraryId") String libraryId) {
        return LibraryAdapter.adaptToLibraryDTO(this.libraryService.obtain(libraryId));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/libraries"})
    public List<LibraryDTO> listAllLibrairies() {
        return LibraryAdapter.adaptToLibraryDTOList(this.libraryService.listAll());
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/libraries/{libraryId}"})
    public void updateLibrary(@PathVariable("libraryId") String libraryId, @RequestBody LibraryDTO libraryDTO) {
        this.libraryService.update(libraryId, LibraryAdapter.transformToLibrary(libraryDTO));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/libraries/{libraryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeLibrary(@PathVariable("libraryId") String libraryId) {
        this.libraryService.remove(libraryId);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/libraries/type/{type}"})
    public List<LibraryDTO> listAllLibrairiesByType(@PathVariable("type") Type type) {
        return LibraryAdapter.adaptToLibraryDTOList(this.libraryService.listAllByType(type));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/libraries/director/surname/{surname}"})
    public List<LibraryDTO> listAllLibrairiesByDirectorName(@PathVariable("surname") String surname) {
        return LibraryAdapter.adaptToLibraryDTOList(this.libraryService.listAllByDirectorName(surname));
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/libraries/{libraryId}/books"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addBookToLibrary(@PathVariable("libraryId") String libraryId, @RequestBody LibraryDTO.BookDTO bookDTO) {
        this.libraryService.addBook(libraryId, LibraryAdapter.transformToBook(bookDTO));
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/libraries/{libraryId}/books/{bookId}"})
    public void updateBook(@PathVariable("libraryId") String libraryId, @PathVariable("bookId") Long bookId, @RequestBody LibraryDTO.BookDTO bookDTO) {
        this.libraryService.updateBook(libraryId, bookId, LibraryAdapter.transformToBook(bookDTO));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/libraries/{libraryId}/books"})
    public List<LibraryDTO.BookDTO> listAllBooksFromLibrary(@PathVariable("libraryId") String libraryId) {
        List<Book> books = this.libraryService.listAllBooks(libraryId);
        return LibraryAdapter.adaptToBookListDTO(books);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/libraries/{libraryId}/books/{bookId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookFromLibrary(@PathVariable("libraryId") String libraryId, @PathVariable("bookId") Long bookId) {
        this.libraryService.removeBook(libraryId, bookId);
    }

}
