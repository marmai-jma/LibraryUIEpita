package com.bnpparibas.itg.mylibraries.libraries.domain.exception;

import java.util.HashSet;
import java.util.Set;

public class MyAppBookException extends RuntimeException {
    private Set<String> codeErreurs = new HashSet<>();

    public MyAppBookException(String codeErreur) {
        this.codeErreurs.add(codeErreur);
    }

    public MyAppBookException(Set<String> codeErreurs) {
        this.codeErreurs = codeErreurs;
    }

    public Set<String> getCodeErreurs() {
        return codeErreurs;
    }
}