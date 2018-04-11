package com.elypia.elypia.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import javax.websocket.Session;
import java.time.Instant;

@Entity("accounts")
public class Account {

    @Id
    private ObjectId objectId;

    @Property("id")
    private int id;

    @Property("email")
    private String[] email;

    @Property("username")
    private String username;

    @Property("avatar")
    private String avatar;

    @Property("hash")
    private String hash;

    @Property("api_key")
    private String apiKey;

    @Embedded("token")
    private SessionToken[] tokens;

    @Embedded("verification")
    private Verification verification;

    @Property("admin")
    private boolean admin;

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public SessionToken[] getTokens() {
        return tokens;
    }

    public void setTokens(SessionToken[] tokens) {
        this.tokens = tokens;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
