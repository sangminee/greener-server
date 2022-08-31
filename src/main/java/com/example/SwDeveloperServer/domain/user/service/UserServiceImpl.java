package com.example.SwDeveloperServer.domain.user.service;

import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import com.example.SwDeveloperServer.domain.user.repository.dto.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.PostJoinRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.PostLoginReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.PostLoginRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UerService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public PostJoinRes join(PostJoinReq postJoinReq) {

        User user = new User();
        user.setEmail(postJoinReq.getEmail());
        user.setPassword(postJoinReq.getPassword());
        user.setName(postJoinReq.getName());
        user.setNickname(postJoinReq.getNickname());
        user.setUserPhotoUrl(postJoinReq.getUserPhotoUrl());
        user.setPhone(postJoinReq.getPhone());
        user.setUserCreateTime(LocalDateTime.now());

        user.setUserServiceAgreement(postJoinReq.getUserServiceAgreement());
        user.setPhoneAgreement(postJoinReq.getPhoneAgreement());
        user.setUserType(postJoinReq.getUserType());
        user.setState(postJoinReq.getState());

        userJpaRepository.save(user);

        return new PostJoinRes(user.getUserId(), "회원가입이 완료되었습니다.");
    }

    @Override
    public PostLoginRes login(PostLoginReq postLoginReq) {
        List<User> list = userJpaRepository.findAll();

        User user = new User();
        for(int i=0; i<list.size(); i++){
            if(postLoginReq.getEmail().equals(postLoginReq.getEmail())){
                user = list.get(i);
                break;
            }
        }

        if(user.equals(null)){

        }
        if(user.getPassword().equals(postLoginReq.getPassword())){
            return new PostLoginRes("로그인을 성공했습니다.");
        }

        return null;
    }
}
