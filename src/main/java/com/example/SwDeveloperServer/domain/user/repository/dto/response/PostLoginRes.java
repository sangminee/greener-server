package com.example.SwDeveloperServer.domain.user.repository.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLoginRes {
    @ApiModelProperty(notes = "로그인 성공 메세지")
    private String message;

    @ApiModelProperty(notes = "jwt")
    private String jwt;

    public PostLoginRes(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }
}
