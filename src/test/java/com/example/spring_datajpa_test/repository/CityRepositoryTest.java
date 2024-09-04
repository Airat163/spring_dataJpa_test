package com.example.spring_datajpa_test.repository;

import com.example.spring_datajpa_test.model.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CityRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CityRepository cityRepository;

 /*   @BeforeEach
    void init() {
        City city = new City("Volgograd", 450L, 900L, "Pypkin");
        City city2 = new City("Samara", 450L, 900L, "Pypkin");
        City city3 = new City("Moscow", 450L, 900L, "Pypkin");
        testEntityManager.persist(city);
        testEntityManager.persist(city2);
        testEntityManager.persist(city3);
    }*/

    @Test
    void shouldReturnString() {
        City city = new City("Volgograd", 450L, 900L, "Pypkin");
        City citySave = testEntityManager.persist(city);
        Assertions.assertEquals("Volgograd",
                cityRepository.findByName(citySave.getName()).get().getName());
    }

    @Test
    void shouldReturnListCities() {
        Assertions.assertEquals(3,cityRepository.findAll().size());
        Assertions.assertEquals("Samara",cityRepository.findAll().get(1).getName());
        Assertions.assertNotNull(cityRepository.findAll().get(0));
    }

    @Test
    void shouldReturnCityById() {
        City city = new City("Volgograd", 450L, 900L, "Pypkin");
        testEntityManager.persist(city);
        Assertions.assertEquals(city.getName(),cityRepository.findById(1).get().getName());
    }

    @Test
    void shouldDeleteCity() {
        City city = new City("Volgograd", 450L, 900L, "Pypkin");
        testEntityManager.persist(city);
        testEntityManager.remove(city);
        City city1 = testEntityManager.find(City.class, 1);
        Assertions.assertNull(city1);
    }

    @Test
    void shouldUpdateCity() {
        City city = new City("Volgograd", 450L, 900L, "Pypkin");
        testEntityManager.persist(city);
        city.setName("Samara");
        Assertions.assertNotEquals("Volgograd",
                cityRepository.findByName(city.getName()).get().getName());
        Assertions.assertEquals("Samara",cityRepository.findAll().get(0).getName());

    }
}