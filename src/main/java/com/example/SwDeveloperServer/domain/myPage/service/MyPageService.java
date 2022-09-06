package com.example.SwDeveloperServer.domain.myPage.service;

import com.example.SwDeveloperServer.domain.myPage.entity.Stamp;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.requset.ChangeNameReq;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.requset.ChangeNicknameReq;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.requset.ChangeUserPhotoReq;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.response.ChangeRes;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.response.GetMyPage;

import java.util.List;

public interface MyPageService {

    GetMyPage getMyPage(Long userId);
    List<Stamp> getStamps(Long userId);
    Stamp getStamp(Long userId, String stampId);

    ChangeRes changeNickname(Long userId, ChangeNicknameReq changeNicknameReq);
    ChangeRes changeName(Long userId, ChangeNameReq changeNameReq);
    ChangeRes changeUserPhoto(Long userId, ChangeUserPhotoReq changeUserPhotoReq);
}
