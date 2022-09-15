package com.example.SwDeveloperServer.domain.community.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
public class PostChallengeReq {

    private String challengeTitle;
    private String challengeTitlePhoto;

    private int successPoint;  // 성공 시 획득 포인트
    private int limitPeople; // 인원제한

    @ApiModelProperty(notes = "챌린지 시작 날짜", example="2022-09-09")
    private String toStartDate;
    @ApiModelProperty(notes = "챌린지 종료 날짜", example="2022-10-09")
    private String toEndDate;

    @ApiModelProperty(notes = "첨부 사진")
    private Queue<String> getPhoto;
    @ApiModelProperty(notes = "첨부 태그")
    private Queue<String> getTag;
    @ApiModelProperty(notes = "참여 방법")
    private Queue<String> getHowTo;

}
