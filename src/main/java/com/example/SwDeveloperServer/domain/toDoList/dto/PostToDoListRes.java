package com.example.SwDeveloperServer.domain.toDoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostToDoListRes {
    @ApiModelProperty(example = "투두에 등록되었습니다.")
    private String message;

    public PostToDoListRes(String message) {
        this.message =message;
    }
}
