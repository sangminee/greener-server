package com.example.SwDeveloperServer.domain.shop.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@ApiModel(description = "홈 화면")
@Setter
public class PostItemReq {
    private String itemName;
    private int purchasePoint;
    private String itemPhoto;
}
