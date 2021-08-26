package com.example.quanlylichthi.ui.taikhoan;

public class User {
    private String username;
    private String password;
    //user = 0; admin = 1
    private int admin;

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

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }


    @Override
    public String toString() {
        String type;
        if(this.admin == 1)
            type = "Admin";
        else
            type = "User";
        return "username: "+this.username+"\nPassword: "+password+"\nType account: "+type;
    }
}
