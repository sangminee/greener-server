package com.example.SwDeveloperServer.domain.myPage.service;

import com.example.SwDeveloperServer.domain.myPage.entity.Stamp;
import com.example.SwDeveloperServer.domain.myPage.repository.PointRepository;
import com.example.SwDeveloperServer.domain.myPage.repository.StampRepository;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.requset.ChangeNameReq;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.requset.ChangeNicknameReq;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.requset.ChangeUserPhotoReq;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.response.ChangeRes;
import com.example.SwDeveloperServer.domain.myPage.repository.dto.response.GetMyPage;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageServiceImpl implements MyPageService{

    private final UserJpaRepository userJpaRepository;
    private final StampRepository stampRepository;
    private final PointRepository pointRepository;

    public MyPageServiceImpl(UserJpaRepository userJpaRepository, StampRepository stampRepository, PointRepository pointRepository) {
        this.userJpaRepository = userJpaRepository;
        this.stampRepository = stampRepository;
        this.pointRepository = pointRepository;
    }

    @Override
    public GetMyPage getMyPage(Long userId) {
        return null;
    }

    @Override
    public List<Stamp> getStamps(Long userId) {
        List<Stamp> list = stampRepository.findAll();
        List<Stamp> getStamps = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getUser().getUserId() == userId){
                getStamps.add(list.get(i));
            }
        }
        return getStamps;
    }

    @Override
    public Stamp getStamp(Long userId, String stampId) {
        return null;
    }


    @Override
    public ChangeRes changeNickname(Long userId, ChangeNicknameReq changeNicknameReq) {
        Optional<User> user = userJpaRepository.findById(userId);
        user.get().setNickname(changeNicknameReq.getNickname());
        userJpaRepository.save(user.get());
        return new ChangeRes("닉네임 변경이 완료되었습니다.");
    }

    @Override
    public ChangeRes changeName(Long userId, ChangeNameReq changeNameReq) {
        Optional<User> user = userJpaRepository.findById(userId);
        user.get().setName(changeNameReq.getName());
        userJpaRepository.save(user.get());
        return new ChangeRes("이름 변경이 완료되었습니다.");
    }

    @Override
    public ChangeRes changeUserPhoto(Long userId, ChangeUserPhotoReq changeUserPhotoReq) {
        Optional<User> user = userJpaRepository.findById(userId);
        user.get().setUserPhotoUrl(changeUserPhotoReq.getUserPhotoUrl());
        userJpaRepository.save(user.get());
        return new ChangeRes("프로필 사진 변경이 완료되었습니다.");
    }
}
