package com.groupe6.babycare.dtos.auth;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    public RegisterRequest(String firstName, String lastName, String email, String password, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
