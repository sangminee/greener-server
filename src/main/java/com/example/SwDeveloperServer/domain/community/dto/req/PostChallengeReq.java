package com.example.SwDeveloperServer.domain.community.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostChallengeReq {

    private String challengeTitle;
    private String challengeTitlePhoto;
    private String challengePostContent;

    @ApiModelProperty(notes = "챌린지 시작 날짜", example="2022-09-09")
    private String toStartDate;
    @ApiModelProperty(notes = "챌린지 종료 날짜", example="2022-10-09")
    private String toEndDate;


//    private LocalDateTime goalAlarmTime;
}
