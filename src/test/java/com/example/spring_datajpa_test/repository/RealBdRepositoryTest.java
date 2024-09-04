package com.example.spring_datajpa_test.repository;

import com.example.spring_datajpa_test.model.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RealBdRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void shouldReturnCity() {
        City city = new City("Tolyati", 500L, 412L, "Ivanov");
        cityRepository.save(city);
        Assertions.assertEquals("Tolyati",
                cityRepository.findByName(city.getName()).get().getName());
    }

    @Test
    void shouldReturnListCity() {
        List<City> cityList = cityRepository.findAll();
        Assertions.assertNotNull(cityList);
        Assertions.assertEquals("Samara",cityList.get(1).getName());
    }

    @Test
    void shouldReturnCityById() {
        Assertions.assertEquals("Azarov",
                cityRepository.findById(10).get().getSecretary());
    }

    @Test
    void shouldDeleteCityById() {
        cityRepository.deleteById(4);
        Assertions.assertEquals(3,cityRepository.findAll().size());
    }

    @Test
    void shouldUpdateCityById() {
        City city = cityRepository.findById(4).get();
        city.setName("Piter");
        cityRepository.save(city);
        Assertions.assertEquals("Piter",
                cityRepository.findById(4).get().getName());
    }

    @Test
    void shouldReturnSumBudget() {
        int sumBudget = 25600;
        Assertions.assertEquals(sumBudget, cityRepository.sumBudget());
    }

    @Test
    void shouldReturnListCitySortByCountPeoplesByDesc() {
        List<City> cityList = cityRepository.findAll();
        Assertions.assertEquals(20_000_000,cityList.get(0).getCount_peoples());
    }
}
