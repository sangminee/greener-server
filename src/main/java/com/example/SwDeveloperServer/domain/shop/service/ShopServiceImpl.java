package com.example.SwDeveloperServer.domain.shop.service;

import com.example.SwDeveloperServer.domain.shop.dto.GetShopRes;
import com.example.SwDeveloperServer.domain.shop.dto.PostItemReq;
import com.example.SwDeveloperServer.domain.shop.entity.Item;
import com.example.SwDeveloperServer.domain.shop.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService{

    private final ItemRepository itemRepository;

    public ShopServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public GetShopRes getShop(Long userId) {
        return null;
    }

    @Override
    public GetShopRes purchaseItem(Long userId) {
        return null;
    }

    @Override
    public String setItem(PostItemReq postItemReq) {

        Item item = new Item();
        item.setItemName(postItemReq.getItemName());
        item.setItemPhoto(postItemReq.getItemPhoto());
        item.setPurchasePoint(postItemReq.getPurchasePoint());
        itemRepository.save(item);

        return "아이템추가가 완료되었습니다.";
    }
}
