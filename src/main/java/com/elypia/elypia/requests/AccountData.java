package com.elypia.elypia.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AccountData {

    @NotEmpty(message = "Email must be provided in order to signup.")
    @Email(message = "Input in the email field is not a vaild email.")
    private String email;

    @NotEmpty(message = "Username must be provided in order to signup.")
    private String username;

    @NotEmpty(message = "Password must be provided in order to signup.")
    private String password;

    @NotEmpty(message = "Please confirm your password then try again.")
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String hash) {
        this.password = hash;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
