package com.todoapp.registration;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class RegistrationData implements Serializable {
    @NotEmpty(message = "Login cannot by empty")
    private String login;
    @NotEmpty(message = "Password need to be setted up.")
    private String password;
    private String passwordConfirm;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
