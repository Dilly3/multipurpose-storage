package com.dilly3.multipurposedrive.model;

import javax.persistence.*;


 @Entity public class IUser {

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String firstname;
    private String lastname;
    @Column(columnDefinition = "VARCHAR(512) NOT NULL") private String password;
    private String salt;

    public IUser() {
    }

    public IUser(String username, String firstname, String lastname, String password, String salt) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.salt = salt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
