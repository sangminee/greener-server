package com.example.SwDeveloperServer.domain.home.service;

import com.example.SwDeveloperServer.domain.home.dto.GetHomeRes;
import com.example.SwDeveloperServer.domain.shop.repository.ItemRepository;

public interface HomeService {

    GetHomeRes getHome(Long userId);
}
