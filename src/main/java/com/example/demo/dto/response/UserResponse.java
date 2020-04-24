package com.example.demo.dto.response;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Component
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 4743241484582984675L;
    private long Id;
    private String firstName;
    private String lastName;
    private String zip;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
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

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}

