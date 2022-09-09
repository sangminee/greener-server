package com.example.SwDeveloperServer.domain.toDoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDailyTodoRes {
    @ApiModelProperty(example = "Daily To do가 설정되었습니다.")
    private String message;

    public PostDailyTodoRes(String message) {
        this.message =message;
    }
}
