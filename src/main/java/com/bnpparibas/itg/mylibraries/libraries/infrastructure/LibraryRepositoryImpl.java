package com.bnpparibas.itg.mylibraries.libraries.infrastructure;

import com.bnpparibas.itg.mylibraries.libraries.domain.ddd.DDD;
import com.bnpparibas.itg.mylibraries.libraries.domain.exception.ErrorCodes;
import com.bnpparibas.itg.mylibraries.libraries.domain.exception.MyAppBookException;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Library;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.LibraryRepository;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@DDD.RepositoryImpl
@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

    @Autowired
    private LibraryDAO libraryDAO;

    @Override
    public Library get(Long id) {
        return libraryDAO.findById(id).map(LibraryJPA::toLibrary).orElseThrow(() -> new MyAppBookException(ErrorCodes.LIBRARY_NOT_FOUND));
    }

    @Override
    public String save(Library library) {
        LibraryJPA libraryJPA = libraryDAO.save(new LibraryJPA(library));
        return libraryJPA.getId();
    }

    @Override
    public List<Library> findAll() {
        return libraryDAO.findAll().stream().map(LibraryJPA::toLibrary).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        libraryDAO.deleteById(id);
    }

    @Override
    public List<Library> findByType(Type type) {
        return this.libraryDAO.findByType(type);
    }

    @Override
    public List<Library> findByDirectorSurname(String surname) {
        return this.libraryDAO.findByDirectorSurname(surname);
    }

    @Override
    public List<Library> searchByDirectorNameQuery(String surname) {
        return this.libraryDAO.searchByDirectorNameQuery(surname);
    }

    @Override
    public List<Library> searchByDirectorNameNativeQuery(String surname) {
        return this.libraryDAO.searchByDirectorNameNativeQuery(surname);
    }
}
