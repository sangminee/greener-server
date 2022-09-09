package com.example.SwDeveloperServer.domain.toDoList.dto;

import com.example.SwDeveloperServer.domain.toDoList.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
public class GetToDoListRes {
    private Queue<Todo> individualList;
    private Queue<Todo> challengeList;
}
