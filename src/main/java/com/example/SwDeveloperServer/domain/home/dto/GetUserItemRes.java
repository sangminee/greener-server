package com.example.SwDeveloperServer.domain.home.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "사용자 아이템 정보")
@Setter
public class GetUserItemRes {
    // - Item
    private long itemId;
    private String itemName; // 아이템 이름
    private String itemPhoto; // 아이템 사진

    // - UserItem
    private int itemQuantity; // 수량
}
