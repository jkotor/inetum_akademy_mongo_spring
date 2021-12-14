package com.inetum.academy.mongo;

import com.inetum.academy.mongo.tolkien.Hero;
import com.inetum.academy.mongo.tolkien.HeroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static com.inetum.academy.mongo.tolkien.Hero.Race.HOBBIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest(
        properties = {"spring.mongodb.embedded.version=4.0.12"}
)
public class HeroRepositoryCrudTest {

    @Autowired
    HeroRepository repository;

    @Test
    void insertsSingleDocument() {
        //when
        Hero frodo = repository.save(Hero.builder().name("Frodo").race(HOBBIT).build());

        //then
        assertEquals("Frodo", frodo.getName());
        System.out.println(" " + frodo.toString());
    }

    @Test
    void basicTwoDocumentCrudScenario() {
        // w tym teście użyć każdorazowo findAll() do wyszukania postaci z bazy danych
        // opcjonalnie można wyświetlić zawartość bazy
        // when - wstawić do bazy 2 dowolne postacie

        // then - asercja na count() oraz na któreś property wybranych postaci

        // when - dodać jednej postaci broń

        // then - asercja że w bazie nastąpiła zmiana

        // when - zmienić jednej postaci imię

        // then - asercja

        // when - usunąć jedną postać

        // then - asercja

    }


}
