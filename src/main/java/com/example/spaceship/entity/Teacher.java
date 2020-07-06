package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
        @MapsId
        private User user;


        @OneToMany(mappedBy = "teacher")
        private List<Course> courses;
        @Column(columnDefinition = "timestamp default current_timestamp",
                insertable = false,
                updatable = false)
        private LocalDateTime insertTime;
        @Column(columnDefinition = "timestamp default current_timestamp"+" on update current_timestamp",
                insertable = false,
                updatable = false)
        private LocalDateTime updateTime;

}
