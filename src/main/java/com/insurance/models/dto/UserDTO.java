package com.insurance.models.dto;

import com.insurance.validation.Registration;
import jakarta.validation.constraints.*;

public class UserDTO {

    private Long userId;

    @NotBlank(message = "Enter your first name")
    private String firstName;

    @NotBlank(message = "Enter your last name")
    private String lastName;

    @Email(message = "Enter valid email address")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{1,4}[\\s\\-]?[0-9]{1,13}(?:[\\s\\-]?[0-9]{1,13})*$", message = "Enter valid phone number")
    private String phoneNumber;

    @NotBlank(message = "Enter your password", groups = Registration.class)
    private String password;

    @NotBlank(message = "Enter your password", groups = Registration.class)
    private String confirmPassword;

    @NotBlank(message = "Enter your address")
    private String streetAndNumber;

    @NotBlank(message = "Enter your city")
    private String city;

    @Pattern(regexp = "^[A-Za-z0-9 -]{3,10}$", message = "Enter valid ZIP code")
    private String zipCode;

    @NotBlank(message = "Enter your country")
    private String country;

    //Region: Getters & Setters


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Endregion: Getters & Setters

}
