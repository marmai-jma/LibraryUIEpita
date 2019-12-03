package com.bnpparibas.itg.mylibraries.libraries.domain.library;

import com.bnpparibas.itg.mylibraries.libraries.domain.ddd.DDD;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@DDD.ValueObject
@Embeddable
public class Address {


    private int number;

    private String street;

    private int postalCode;

    private String city;

    public Address() {}

    public Address(int number, String street, int postalCode, String city) {
        this.number = number;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number &&
                postalCode == address.postalCode &&
                street.equals(address.street) &&
                city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, street, postalCode, city);
    }
}
