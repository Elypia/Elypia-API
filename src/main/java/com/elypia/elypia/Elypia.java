package com.elypia.elypia;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Elypia {

    public static Morphia morphia;
    public static Datastore store;

    public static void main(String[] args) {
        morphia = new Morphia();
        morphia.mapPackage("com.elypia.shooket.entities");
        store = morphia.createDatastore(new MongoClient(), "elypia");

        SpringApplication.run(Elypia.class, args);
    }
}
