package com.example.SwDeveloperServer.domain.user.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "이메일 찾기")
@Setter
public class PostFindEmailReq {
    private String phone;
}
