package com.example.SwDeveloperServer.domain.user.repository.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostJoinRes {

    @ApiModelProperty(notes = "회원 일련 번호")
    private long userId;
    @ApiModelProperty(notes = "회원가입 성공 메세지")
    private String message;

    public PostJoinRes(long userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
