package com.inetum.academy.mongo;


import com.inetum.academy.mongo.tolkien.Hero;
import com.inetum.academy.mongo.tolkien.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.stream.Collectors;

import static com.inetum.academy.mongo.tolkien.Hero.MagicType.BLACK;
import static com.inetum.academy.mongo.tolkien.Hero.MagicType.WHITE;
import static com.inetum.academy.mongo.tolkien.Hero.Race.*;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest(
        properties = {"spring.mongodb.embedded.version=4.0.12"
//                    , "logging.level.org.springframework.boot.autoconfigure.mongo.embedded=warn"
//                    , "logging.level.org.mongodb=info"
//                    , "logging.level.org.springframework.data.mongodb.core.MongoTemplate=info"
        }
)
public class HeroRepositorySearchTest {

    @Autowired
    HeroRepository repository;

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
        bilbo.setCurrentMood("unhappy");
        frodo.setCurrentMood("overtaken by the ring");

        repository.saveAll(asList(bilbo, frodo, legolas, elrond, boromir, aragorn, saruman, gandalf));
    }

    @Test
    void countAndPresentHeroes() {
        out.println("number of heroes: " + repository.count());
        out.println(repository.findAll().stream()
                .map(Hero::toString)
                .collect(Collectors.joining("\n")));
    }

    // https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#query-by-example

    @Test
    void findAllElfs() {
        //given
        Example<Hero> criteria = Example.of(Hero.builder().race(ELF).build());

        //when
        List<Hero> found = repository.findAll(criteria);

        //then
        assertThat(found).hasSize(2)
                .extracting("name").containsOnly("Legolas", "Elrond");
    }

    @Test
    void findByRaceAndSort() {
        // znaleźć wszystkich ludzi i posortować na bazie po "name"
        // Sort.by()

    }

    @Test
    void pageAndSortWhenSearchingByRace() {
        // znaleźć wszystkich ludzi i posortować na bazie po "name", wyciągać stronami po 2 elementy
        // Pageable, PageRequest.of()

    }

    @Test
    void findAllYoungHeroesSortedByAge() {
        // Znaleźć wszystkich bohaterów, których wiek jest mniejszy niż 100. Wiek musi być liczbowy
        // findAllByAgeLessThan

    }




}
