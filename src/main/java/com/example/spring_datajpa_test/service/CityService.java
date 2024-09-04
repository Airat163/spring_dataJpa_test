package com.example.spring_datajpa_test.service;

import com.example.spring_datajpa_test.model.City;
import com.example.spring_datajpa_test.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityService {
    private final CityRepository cityDao;

    public City save(City city) {
        return cityDao.save(city);
    }

    public List<City> findAll() {
        return cityDao.findAll();
    }

    public City findById(int id) {
        return cityDao.findById(id).get();
    }

    public void delete(int id) {
        cityDao.deleteById(id);
    }

    public City update(int id, City city) {
        City cityOld = findById(id);
        cityOld.setName(city.getName());
        cityOld.setBadget(city.getBadget());
        cityOld.setSecretary(city.getSecretary());
        cityOld.setCount_peoples(city.getCount_peoples());
        return cityDao.save(cityOld);
    }

    public Integer sumBudget() {
        return cityDao.sumBudget();
    }

    public List<City> sortByCountPeoplesByDesc() {
        return cityDao.sortByCountPeoplesByDesc();
    }
}
