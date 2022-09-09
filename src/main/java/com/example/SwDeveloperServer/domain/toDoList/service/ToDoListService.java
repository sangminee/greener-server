package com.example.SwDeveloperServer.domain.toDoList.service;

import com.example.SwDeveloperServer.domain.myPage.dto.response.GetMyPage;
import com.example.SwDeveloperServer.domain.toDoList.dto.*;
import com.example.SwDeveloperServer.utils.response.BaseException;

import java.text.ParseException;

public interface ToDoListService {
    PostDailyTodoRes setDailyTodoList() throws ParseException, BaseException ;

    GetToDoListRes getTodo(Long userId, String date) throws ParseException, BaseException;

    PostTodoRes postPost(Long todoId, PostTodoReq postTodoReq);

    PostToDoListRes setTodoList(Long userId, PostToDoListReq postToDoListReq);
}
