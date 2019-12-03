package com.bnpparibas.itg.mylibraries.libraries.exposition;

import com.bnpparibas.itg.mylibraries.libraries.domain.exception.ErrorCodes;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.Type;
import com.bnpparibas.itg.mylibraries.libraries.domain.library.book.LiteraryGenre;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class LibraryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String id;

    @JsonProperty
    String label;

    @JsonProperty
    Type type;

    @NotNull(message = ErrorCodes.LIBRARY_MUST_HAVE_AN_ADDRESS)
    @JsonProperty
    AddressDTO addressDTO;

    @Valid
    @NotNull(message = ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR)
    @JsonProperty
    DirectorDTO directorDTO;

    @JsonProperty
    List<BookDTO> bookDTOList;

    public LibraryDTO() {

    }

    public LibraryDTO(String id, String label, Type type, AddressDTO addressDTO, DirectorDTO directorDTO, List<BookDTO> bookDTOList) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.addressDTO = addressDTO;
        this.directorDTO = directorDTO;
        this.bookDTOList = bookDTOList;
    }

    @Getter
    public static class DirectorDTO {

        @NotBlank(message = ErrorCodes.DIRECTOR_MUST_HAVE_A_SURNAME)
        @JsonProperty("firstname") String surname;

        @NotBlank(message = ErrorCodes.DIRECTOR_MUST_HAVE_A_NAME)
        @JsonProperty("lastname") String name;

        public DirectorDTO(String surname, String name) {
            this.surname = surname;
            this.name = name;
        }
    }

    public static class AddressDTO {
        @JsonProperty("numberStreet") int number;
        @JsonProperty String street;
        @JsonProperty int postalCode;
        @JsonProperty String city;

        public AddressDTO(int number, String street, int postalCode, String city) {
            this.number = number;
            this.street = street;
            this.postalCode = postalCode;
            this.city = city;
        }
    }

    public static class BookDTO {
        @JsonProperty String title;
        @JsonProperty String author;
        @JsonProperty int numberOfPage;
        @JsonProperty LiteraryGenre literaryGenre;

        public BookDTO(String title, String author, int numberOfPage, LiteraryGenre literaryGenre) {
            this.title = title;
            this.author = author;
            this.numberOfPage = numberOfPage;
            this.literaryGenre = literaryGenre;
        }
    }

}
