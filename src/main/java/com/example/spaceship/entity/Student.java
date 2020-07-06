package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @MapsId
    private User user;
    @OneToMany(mappedBy ="student" )
    private List<Elective> electives;
    @OneToMany(mappedBy = "student")
    private List<LearnedResource> learnedResources;

    @OneToMany(mappedBy = "student")
    private List<AnswerQuestion> answerQuestions;

    @OneToMany(mappedBy = "student")
    private List<AnswerTestQuestion> answerTestQuestions;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp"+" on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

}
