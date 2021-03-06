package it.giorgiaauroraadorni.booktique.model;

import it.giorgiaauroraadorni.booktique.utility.EntityEqualsByAttributes;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer extends Person implements EntityEqualsByAttributes {

    @Column(unique = true, length = 32, nullable = false)
    @Size(min = 5)
    private String username;

    @Column(length = 128, nullable = false)
    @Size(min = 8)
    private String password;

    // This is an italian vat number.
    @Column(length = 13)
    @Pattern(regexp = "^(IT)?[0-9]{11}$")
    private String vatNumber;

    // This is a full postal address for the contact represented by this object.
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    private Address address;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equalsByAttributes(Object expectedObject, boolean optionalId) {
        if (this == expectedObject) return true;
        if (!(expectedObject instanceof Customer)) return false;
        Customer customer = (Customer) expectedObject;
        if(!(super.equalsByAttributes(customer, optionalId))) return false;
        return Objects.equals(getUsername(), customer.getUsername()) &&
                Objects.equals(getPassword(), customer.getPassword()) &&
                getVatNumber().equals(customer.getVatNumber()) &&
                (getAddress() == customer.getAddress() ||
                (getAddress() != null && getAddress().equalsByAttributes(customer.getAddress(), optionalId)));
    }
}
