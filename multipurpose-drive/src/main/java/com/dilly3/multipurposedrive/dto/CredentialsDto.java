package com.dilly3.multipurposedrive.dto;

public class CredentialsDto {
    private int credentialId ;
    private String url;
    private String username;
    private String credKey;
    private String encryptedPassword;
    private String decryptedPassword;
    private int userId;

    public CredentialsDto() {
    }

    public CredentialsDto(int credentialId, String url, String username,
                          String credKey,
                          String encryptedPassword,
                          String decryptedPassword, int userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.credKey = credKey;
        this.encryptedPassword = encryptedPassword;
        this.decryptedPassword = decryptedPassword;
        this.userId = userId;
    }

    public CredentialsDto(int credentialId, String url, String username, String encryptedPassword, String decryptedPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.decryptedPassword = decryptedPassword;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
