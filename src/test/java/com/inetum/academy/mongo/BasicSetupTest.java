package com.inetum.academy.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest(
        properties = {"spring.mongodb.embedded.version=4.0.12"}
)
public class BasicSetupTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void shouldLoadContextAndFindDatabase() {
        System.out.println("Databases: " + mongoTemplate.getDb().getName());
    }
}
