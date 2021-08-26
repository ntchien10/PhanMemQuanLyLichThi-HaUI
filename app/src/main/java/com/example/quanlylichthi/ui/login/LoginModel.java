package com.example.quanlylichthi.ui.login;

public class LoginModel {
    String Username;
    String password;
    int Admin;

    public LoginModel(){}
    public LoginModel(String username, String password, int admin) {
        Username = username;
        this.password = password;
        Admin = admin;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return Admin;
    }

    public void setAdmin(int admin) {
        Admin = admin;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                ", Admin=" + Admin +
                '}';
    }
}
