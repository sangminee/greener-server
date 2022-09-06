package com.example.SwDeveloperServer.domain.myPage.repository.dto.requset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "닉네임 변경")
@Setter
public class ChangeNicknameReq {
    @ApiModelProperty(notes = "닉네임")
    private String nickname;
}
