package com.example.SwDeveloperServer.domain.shop.service;

import com.example.SwDeveloperServer.domain.shop.dto.GetShopRes;
import com.example.SwDeveloperServer.domain.shop.dto.PostItemReq;

public interface ShopService {

    GetShopRes getShop(Long userId);
    GetShopRes purchaseItem(Long userId);

    String setItem(PostItemReq postItemReq);
}
