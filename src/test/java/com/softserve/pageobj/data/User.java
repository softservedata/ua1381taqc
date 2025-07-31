package com.softserve.pageobj.data;

import java.util.Objects;

public class User {
    private long userId;
    private String name;
    private String email;
    private String password;
    private String secretKey;

    public User() {
        userId = 0;
        name =  "";
        email =  "";
        password =  "";
        secretKey =  "";
    }

    public User(long userId, String name, String email, String password, String secretKey) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(secretKey, user.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, password, secretKey);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
