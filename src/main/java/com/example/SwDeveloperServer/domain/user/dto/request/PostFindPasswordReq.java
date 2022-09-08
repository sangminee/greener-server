package com.example.SwDeveloperServer.domain.user.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "비밀번호 찾기")
@Setter
public class PostFindPasswordReq {
    private String email;
}
