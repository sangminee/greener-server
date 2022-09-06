package com.example.SwDeveloperServer.domain.myPage.repository.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRes {
    @ApiModelProperty(notes = "변경완료 메세지")
    private String message;

    public ChangeRes(String message) {
        this.message = message;
    }
}
