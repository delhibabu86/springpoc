package com.example.demo.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Component
public class UserRequest implements Serializable{
    /**
     *   if JVM generates different UID during serialization & deserialization , it will create
     *     InvalidClassException. To avoid this we generate UUID specific to file.
     */

    private static final long serialVersionUID = 4304621254523221958L;

    private long id;
    @NotNull(message = "{firstName.NotNull}")
    @Size(min = 5, message = "first name must be min 5 digits")
    private String firstName;
    @NotNull
    @Size(min = 5, message = "Zip must be min 5 digits")
    private String zip;
    private String lastName;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", zip='" + zip + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
