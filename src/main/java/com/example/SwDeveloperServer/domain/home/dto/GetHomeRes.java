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
    private String plantPhotoUrl;
    private int plantLevel;

    // userItem 정보
    @ApiModelProperty(notes = "회원이 가진 아이템 정보")
    private Queue<GetUserItemRes> itemList;
}
