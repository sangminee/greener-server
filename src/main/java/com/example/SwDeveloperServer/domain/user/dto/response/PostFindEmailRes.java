package com.example.SwDeveloperServer.domain.user.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "이메일 찾기")
@Setter
public class PostFindEmailRes {

    @ApiModelProperty(notes = "이메일")
    private String email;

    public PostFindEmailRes(String email) {
        this.email = email;
    }
}
