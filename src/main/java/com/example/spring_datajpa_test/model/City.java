package com.example.spring_datajpa_test.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private Long count_peoples;
    private Long badget;
    private String secretary;

    public City(String name, Long count_peoples, Long badget, String secretary) {
        this.name = name;
        this.count_peoples = count_peoples;
        this.badget = badget;
        this.secretary = secretary;
    }
}
