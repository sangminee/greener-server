package com.example.SwDeveloperServer.domain.user.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "비밀번호 찾기")
@Setter
public class PostFindPasswordRes {

    @ApiModelProperty(notes = "비밀번호")
    private String password;

    public PostFindPasswordRes(String password) {
        this.password = password;
    }
}
