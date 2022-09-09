package com.example.SwDeveloperServer.domain.toDoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTodoRes {
    @ApiModelProperty(example = "인증되었습니다.")
    private String message;

    public PostTodoRes(String message) {
        this.message = message;
    }
}
