package com.example.SwDeveloperServer.domain.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "로그인")
public class PostLoginReq {
    @ApiModelProperty(notes = "이메일")
    private String email;
    @ApiModelProperty(notes = "비밀번호")
    private String password;
}
