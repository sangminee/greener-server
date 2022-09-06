package com.example.SwDeveloperServer.domain.myPage.repository.dto.requset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "프로필 사진 변경")
@Setter
public class ChangeUserPhotoReq {
    @ApiModelProperty(notes = "프로필사진 url")
    private String userPhotoUrl;
}
