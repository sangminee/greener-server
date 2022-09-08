package com.example.SwDeveloperServer.domain.home.service;

import com.example.SwDeveloperServer.domain.home.dto.GetHomeRes;
import com.example.SwDeveloperServer.domain.home.dto.GetUserItemRes;
import com.example.SwDeveloperServer.domain.shop.entity.UserItem;
import com.example.SwDeveloperServer.domain.shop.repository.ItemRepository;
import com.example.SwDeveloperServer.domain.shop.repository.UserItemRepository;
import com.example.SwDeveloperServer.domain.user.entity.PlantPhoto;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.PlantPhotoRepository;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeServiceImpl implements HomeService{

    private final ItemRepository itemRepository;
    private final UserJpaRepository userJpaRepository;
    private final UserItemRepository userItemListRepository;
    private final PlantPhotoRepository plantPhotoRepository;

    public HomeServiceImpl(ItemRepository itemRepository, UserJpaRepository userJpaRepository, UserItemRepository userItemListRepository, PlantPhotoRepository plantPhotoRepository) {
        this.itemRepository = itemRepository;
        this.userJpaRepository = userJpaRepository;
        this.userItemListRepository = userItemListRepository;
        this.plantPhotoRepository = plantPhotoRepository;
    }

    @Override
    public GetHomeRes getHome(Long userId) {
        Optional<User> user = userJpaRepository.findById(userId);
        List<UserItem> allUserItem = userItemListRepository.findAllByUser(user.get());

        Queue<GetUserItemRes> getUserItemResQueue = new LinkedList<>();

        for(int i=0; i<allUserItem.size(); i++){
            GetUserItemRes getUserItemRes = new GetUserItemRes();

            getUserItemRes.setItemId(allUserItem.get(i).getItem().getItemId());
            getUserItemRes.setItemName(allUserItem.get(i).getItem().getItemName());
            getUserItemRes.setItemPhoto(allUserItem.get(i).getItem().getItemPhoto());
            getUserItemRes.setItemQuantity(allUserItem.get(i).getItemQuantity());

            getUserItemResQueue.add(getUserItemRes);
        }

        GetHomeRes getHomeRes = new GetHomeRes();
        getHomeRes.setPlantPhotoUrl(user.get().getPlantPhoto().getPlantPhotoUrl());
        getHomeRes.setPlantLevel(user.get().getPlantPhoto().getPlantLevel());
        getHomeRes.setItemList(getUserItemResQueue);

        return getHomeRes;
    }
}