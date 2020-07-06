package com.example.spaceship.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//考试题目表
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    1为选择题 需要为current，error1,error2,error3赋值，2为填空题，3为判断题需要为current赋值，4为问答题，需要老师进行批阅
    private String title;
    private String detail;
    private Integer type;
    private String current;
    private String error1;
    private String error2;
    private String error3;
    @OneToMany(mappedBy = "testQuestion")
    private List<AnswerTestQuestion> answerTestQuestions;
    @ManyToOne
    private Test test;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp"+" on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;
}
