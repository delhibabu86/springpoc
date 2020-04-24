package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "User")
public class User implements Serializable {
    private static final long serialVersionUID = 2572390464137982913L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @Column(name="first_Name")
    private String firstName;
    @Column(name="last_Name")
    private String lastName;
    @Column(name="zip")
    private String zip;


    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", zip=" + zip +
                '}';
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
