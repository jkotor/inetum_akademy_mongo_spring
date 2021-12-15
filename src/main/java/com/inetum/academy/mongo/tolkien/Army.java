package com.inetum.academy.mongo.tolkien;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "armies")
@Data
@Builder
@AllArgsConstructor
public class Army {

    @Id
    private String id;

    private String name;

    private int soldiersCount;

    private double power;

    @DBRef
    private List<Hero> importantMembers;


}
