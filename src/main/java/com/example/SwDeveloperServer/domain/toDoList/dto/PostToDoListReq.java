package com.example.SwDeveloperServer.domain.toDoList.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
public class PostToDoListReq {
    @ApiModelProperty(example = "투두 설명")
    private String goalDescription;
    @ApiModelProperty(example = "시작 날짜")
    private LocalDate toStartDate;
    @ApiModelProperty(example = "종료 날짜")
    private LocalDate toEndDate;

//    @ApiModelProperty(example = "알림 시간")
//    private Timestamp goalAlarmTime;
}
