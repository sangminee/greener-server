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
    @NotNull@NotBlank
    private String challengeTitlePhoto;

    @NotNull@NotBlank
    private int successPoint;  // 성공 시 획득 포인트
    @NotNull@NotBlank
    private int limitPeople; // 인원제한

    @NotNull@NotBlank
    @ApiModelProperty(notes = "챌린지 시작 날짜", example="2022-09-09")
    private String toStartDate;

    @NotNull@NotBlank
    @ApiModelProperty(notes = "챌린지 종료 날짜", example="2022-10-09")
    private String toEndDate;

    @Null
    @ApiModelProperty(notes = "첨부 사진")
    private Queue<String> getPhoto;

    @Null
    @ApiModelProperty(notes = "첨부 태그")
    private Queue<String> getTag;

    @NotNull@NotBlank
    @ApiModelProperty(notes = "참여 방법")
    private Queue<String> getHowTo;

}
