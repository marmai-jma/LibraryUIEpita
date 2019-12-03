package com.bnpparibas.itg.mylibraries.libraries.application;

import com.bnpparibas.itg.mylibraries.libraries.domain.ddd.DDD;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Library;
import com.bnpparibas.itg.mylibraries.libraries.domain.exception.ErrorCodes;
import com.bnpparibas.itg.mylibraries.libraries.domain.exception.MyAppBookException;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.LibraryRepository;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Type;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.Book;
import com.bnpparibas.itg.mylibraries.libraries.infrastructure.LibraryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@DDD.ApplicationService
@Transactional
@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public String create(Library newLibrary) {
        return this.libraryRepository.save(newLibrary);
    }

    public Library obtain(Long id) {
        return this.libraryRepository.get(id);
    }

    public List<Library> listAll() {
        return this.libraryRepository.findAll();
    }

    public void update(Long id, Library libraryWithNewInformations) {
        Library library = obtain(id);
        library.update(libraryWithNewInformations);
        this.libraryRepository.save(library);
    }

    public void remove(Long id) {
        obtain(id);
        this.libraryRepository.delete(id);
    }

    public List<Library> listAllByType(Type type) {
        return this.libraryRepository.findByType(type);
    }

    public List<Library> listAllByDirectorName(String surname) {
        return this.libraryRepository.findByDirectorSurname(surname);
    }

    public void addBook(Long libraryId, Book book) {
        Library library = obtain(libraryId);
        library.addBook(book);
        this.libraryRepository.save(library);
    }

    public void updateBook(Long libraryId, Long bookId, Book book) {
        Library library = obtain(libraryId);
        library.updateBook(bookId, book);
        this.libraryRepository.save(library);
    }

    public void removeBook(Long libraryId, Long bookId) {
        Library library = obtain(libraryId);
        library.removeBook(bookId);
        this.libraryRepository.save(library);
    }

    public List<Book> listAllBooks(Long libraryId) {
        Library library = obtain(libraryId);
        return Collections.unmodifiableList(library.getBooks());
    }
}
