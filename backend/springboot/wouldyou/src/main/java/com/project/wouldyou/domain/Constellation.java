package com.project.wouldyou.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Constellation {

    @Id
    @Column(name = "constellation_id")
    private Integer id;

    @Column
    private String name;

    @Column
    private String scientificName;

    @Column
    private String englishName;

    @Column
    private String location;

    @Column
    private String culmination;

    @Column
    private LocalDateTime culminationDatetime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String image;
}