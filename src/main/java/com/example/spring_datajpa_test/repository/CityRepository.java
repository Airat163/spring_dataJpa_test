package com.example.spring_datajpa_test.repository;

import com.example.spring_datajpa_test.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    Optional<City> findByName(String name);

    @Query(value = "select sum(badget) from city",nativeQuery = true)
    Integer sumBudget();

    @Query(value = "select * from city order by count_peoples desc ",nativeQuery = true)
    List<City> sortByCountPeoplesByDesc();
}
