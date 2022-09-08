package com.example.SwDeveloperServer.domain.home.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "사용자 아이템 정보")
@Setter
public class GetUserItemRes {
    // - Item
    @ApiModelProperty(notes = "아이템 일련 번호")
    private long itemId;
    @ApiModelProperty(notes = "아이템 이름")
    private String itemName;
    @ApiModelProperty(notes = "아이템 사진")
    private String itemPhoto;

    // - UserItem
    @ApiModelProperty(notes = "유저가 가진 아이템 수량")
    private int itemQuantity;
}
