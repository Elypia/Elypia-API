package com.elypia.backend.forms;

import com.elypia.backend.validation.Password;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterForm {

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NonNull
    @NotEmpty
    private String username;

    @NonNull
    @NotEmpty
    @Password
    private String password;

    /**
     * The password verification box, there is no need to
     * apply checks on this as it must be the same as {@link #password}
     * so if that passes the checks, so will this.
     */
    private String verifyPassword;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
