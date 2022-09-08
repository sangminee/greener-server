package com.example.SwDeveloperServer.domain.home.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@ApiModel(description = "홈 화면")
@Setter
public class GetHomeRes {

    // User 정보
    // - PlantPhoto
    @ApiModelProperty(notes = "식물 UI 사진")
    private String plantPhotoUrl;
    @ApiModelProperty(notes = "식물 Level")
    private int plantLevel;

    @ApiModelProperty(notes = "유저가 가진 총 포인트")
    private int pointValue;

    // userItem 정보
    @ApiModelProperty(notes = "회원이 가진 아이템 정보")
    private Queue<GetUserItemRes> itemList;
}
