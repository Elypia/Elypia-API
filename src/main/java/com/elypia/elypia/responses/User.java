package com.elypia.elypia.responses;

import com.elypia.elypia.entities.Account;

public class User {

    private long id;
    private String username;
    private String avatar;
    private boolean verified;
    private boolean admin;

    public User(Account account) {
        id = account.getId();
        username = account.getUsername();
        avatar = account.getAvatar();
        verified = account.getVerification().isVerified();
        admin = account.isAdmin();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
