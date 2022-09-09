package com.example.SwDeveloperServer.domain.myPage.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRes {
    @ApiModelProperty(notes = "변경완료 메세지", example = "변경이 완료되었습니다.")
    private String message;

    public ChangeRes(String message) {
        this.message = message;
    }
}
