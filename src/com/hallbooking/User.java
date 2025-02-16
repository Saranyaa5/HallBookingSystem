package com.hallbooking;

public class User {
    protected String name;
    protected String userId;
    protected String email;
    protected String password;

    public User(String name, String userId, String email, String password) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public String getUserName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return email;
    }
}
