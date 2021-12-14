package com.inetum.academy.mongo.tolkien;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "heroes")
public class Hero {

    public enum Race {
        ELF, HOBBIT, HUMAN, ORC
    }

    public enum MagicType {
        BLACK, WHITE
    }

    @Id
    private String id;

    private String name;

    private Integer age;

    private Race race;

    private List<String> weapons;

    private MagicType magic;

    @Transient
    private String currentMood;

    @Builder
    public Hero(String id, String name, Integer age, Race race, List<String> weapons, MagicType magic) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.race = race;
        this.weapons = weapons;
        this.magic = magic;
    }

}
