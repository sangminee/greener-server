package com.example.SwDeveloperServer.domain.toDoList.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String listGoal;
    private Timestamp toStartDate;
    private Timestamp toEndDate;
    private Timestamp goalAlarmTime;

    private int listGoalRepetition;
    private int state;



}
