package com.dilly3.multipurposedrive.model;

import javax.persistence.*;


@Entity public class Credentials implements Comparable<Credentials> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer credentialId ;
    private String url;
    private String username;
    private String credKey;
    private String password;
     private int userId;

    public Credentials() {
    }

    public Credentials(String url, String username, String credKey, String password, int userId) {
        this.url = url;
        this.username = username;
        this.credKey = credKey;
        this.password = password;
        this.userId = userId;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCredKey() {
        return credKey;
    }

    public void setCredKey(String credKey) {
        this.credKey = credKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(Credentials o) {
        return credentialId.compareTo(o.credentialId);
    }
}
