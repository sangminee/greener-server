package com.example.SwDeveloperServer.domain.myPage.dto.requset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "이름 변경")
@Setter
public class ChangeNameReq {
    @ApiModelProperty(notes = "이름")
    private String name;
}
