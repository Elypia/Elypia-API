package com.elypia.elypia.entities;

import org.bson.internal.Base64;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

import java.util.Random;

@Embedded
public class Verification {

    public static String generate() {
        long l = new Random().nextInt(Integer.MAX_VALUE) + 1000000000;
        return Base64.encode(String.valueOf(l).getBytes());
    }

    @Property("verified")
    private boolean verified;

    @Property("token")
    private String token;;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getConfirmationToken() {
        return token;
    }

    public void setConfirmationToken(String token) {
        this.token = token;
    }
}
