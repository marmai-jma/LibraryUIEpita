package com.bnpparibas.itg.mylibraries.libraries.infrastructure;

import com.bnpparibas.itg.mylibraries.libraries.domain.library.Library;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryDAO extends JpaRepository<LibraryJPA, String > {
    List<Library> findByType(Type type);

    List<Library> findByDirectorSurname(String surname);

    @Query("SELECT library FROM LIBRARY library WHERE library.directorSurname = ?1")
    List<Library> searchByDirectorNameQuery(String surname);

    @Query(value = "SELECT * FROM LIBRARY WHERE DIRECTOR_SURNAME = :surname", nativeQuery = true)
    List<Library> searchByDirectorNameNativeQuery(String surname);
}