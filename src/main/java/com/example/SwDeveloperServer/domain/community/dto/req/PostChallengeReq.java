package com.example.SwDeveloperServer.domain.community.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Queue;

@Getter
@Setter
public class PostChallengeReq {

    @NotNull@NotBlank
    private String challengeTitle;
    @NotNull
    private String challengeTitlePhoto;

    @NotNull
    private int successPoint;  // 성공 시 획득 포인트
    @NotNull
    private int limitPeople; // 인원제한

    @NotNull
    @ApiModelProperty(notes = "챌린지 시작 날짜", example="2022-09-09")
    private String toStartDate;

    @NotNull
    @ApiModelProperty(notes = "챌린지 종료 날짜", example="2022-10-09")
    private String toEndDate;

    @ApiModelProperty(notes = "첨부 사진")
    private Queue<String> getPhoto;

    @ApiModelProperty(notes = "첨부 태그")
    private Queue<String> getTag;

    @ApiModelProperty(notes = "참여 방법")
    private Queue<String> getHowTo;

}
