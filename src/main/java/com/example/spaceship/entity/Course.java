package com.example.demo.entity;

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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String detail;
    private Integer ResourcePercentage;
    private Integer QuestionPercentage;
    private Integer TestPercentage;
    private Integer HomeworkPercentage;

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
