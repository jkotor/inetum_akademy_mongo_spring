package com.inetum.academy.mongo;

import com.inetum.academy.mongo.tolkien.Army;
import com.inetum.academy.mongo.tolkien.ArmyRepository;
import com.inetum.academy.mongo.tolkien.Hero;
import com.inetum.academy.mongo.tolkien.HeroRepository;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.inetum.academy.mongo.tolkien.Hero.MagicType.BLACK;
import static com.inetum.academy.mongo.tolkien.Hero.MagicType.WHITE;
import static com.inetum.academy.mongo.tolkien.Hero.Race.*;
import static com.inetum.academy.mongo.tolkien.Hero.Race.HUMAN;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@DataMongoTest(
        properties = {"spring.mongodb.embedded.version=4.0.12"
//                , "logging.level.org.springframework.boot.autoconfigure.mongo.embedded=warn"
//                , "logging.level.org.mongodb=info"
//                , "logging.level.org.springframework.data.mongodb.core.MongoTemplate=info"
        }
)
public class KingdomsAndArmiesTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    ArmyRepository armyRepository;

    @BeforeEach
    void setUp() {
        Hero bilbo = Hero.builder().id("1").name("Bilbo").race(HOBBIT).age(99).build();
        Hero frodo = Hero.builder().id("2").name("Frodo").race(HOBBIT).age(33).build();
        Hero legolas = Hero.builder().id("3").name("Legolas").race(ELF).age(150).weapons(singletonList("bow")).build();
        Hero elrond = Hero.builder().id("4").name("Elrond").race(ELF).age(250).weapons(singletonList("bow")).magic(WHITE).build();
        Hero boromir = Hero.builder().id("5").name("Boromir").race(HUMAN).age(37).weapons(asList("shield", "sword")).build();
        Hero aragorn = Hero.builder().id("6").name("Aragorn").race(HUMAN).age(48).weapons(singletonList("sword")).build();
        Hero saruman = Hero.builder().id("7").name("Saruman").race(HUMAN).magic(BLACK).build();
        Hero gandalf = Hero.builder().id("8").name("Gandalf").race(HUMAN).magic(WHITE).build();
        heroRepository.saveAll(asList(bilbo, frodo, legolas, elrond, boromir, aragorn, saruman, gandalf));

        Army orcs = Army.builder().soldiersCount(20_000).power(13.5).name("Saruman Army")
                .importantMembers(singletonList(saruman)).build();
        Army gondor = Army.builder().soldiersCount(3_000).power(5.1).name("Gondor")
                .importantMembers(singletonList(boromir)).build();
        Army fellowship = Army.builder().soldiersCount(10).power(0.8).name("The fellowship of the Ring")
                .importantMembers(asList(frodo, legolas, aragorn, gandalf)).build();
        armyRepository.saveAll(asList(orcs, gondor, fellowship));
    }

    @Test
    void findsArmyWithMembers() {
        Optional<Army> maybeArmy = armyRepository.findOne(
                Example.of(
                        Army.builder().name("The fellowship of the Ring").build(),
                        ExampleMatcher.matchingAny()));
        Army army = maybeArmy.get();
        printArmy(army);

    }

    @Test
    void updatesAgeOfAllArmyHeroes() {
        // wyszukać Brygadę Pierścienia oraz jej bohaterów
        // zwiększyć wiek każdego z tych bohaterów o 1
        // sprawdzić jak wyglądają zmiany na bazie po użyciu armyRopository.save()


    }

    @Test
    void retrievesAllHeroesAsProjection() {
        // wyszukać wszystkich bohaterów i zwrócić ich w postaci projekcji na klasę DTO LightHero
        // MongoTemplate


    }

    @Test
    void findArmiesWithPowerAndSoldiers() {
        // przetestować metodę findArmiesWithEnoughPower() wg własnego uznania


    }


    private void printArmy(Army army) {
        out.println("--- Army named '" + army.getName() + "' with following heroes");
        out.println(
                army.getImportantMembers().stream().map(Hero::toString).collect(Collectors.joining("\n"))
        );
        out.println();
    }

    @Data
    private class LightHero {
        private String id;
        private String name;

        @Override
        public String toString() {
            return "Hero {id='" + id + "', name='" + name + "'}";
        }
    }


}
