package com.example.spaceship.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"electiveList"})

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String detail;
    private Integer resourcePercentage;
    private Integer questionPercentage;
    private Integer testPercentage;
    private Integer homeworkPercentage;

    @OneToMany(mappedBy = "course")
    private List<Elective> electiveList;

    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    private List<Test> tests;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp"+" on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

}
