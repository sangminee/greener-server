package com.example.SwDeveloperServer.domain.toDoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
public class PostTodoReq {
    @ApiModelProperty(notes = "사진 url을 입력하세요.")
    private Queue<String> queue;
}
