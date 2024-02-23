package com.example.focus_group.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    @NotEmpty(message = "Логин не может быть пустым")
    private String login;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
