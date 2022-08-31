package com.example.SwDeveloperServer.domain.user.controller;

import com.example.SwDeveloperServer.domain.user.repository.dto.*;
import com.example.SwDeveloperServer.domain.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public PostJoinRes join(@RequestBody PostJoinReq postJoinReq){
        // validation
//        if(postJoinReq.getEmail() == null) ;
//        if(postJoinReq.getPassword() == null) ;
//        if(postJoinReq.getName() == null) ;
//        if(postJoinReq.getNickname() == null) ;
//        if(postJoinReq.getUserPhotoUrl() == null) ;
//        if(postJoinReq.getPhone() == null) ;

        PostJoinRes postJoinRes = userService.join(postJoinReq);

        if(postJoinRes == null) logger.info("회원가입 실패");
        else logger.info("회원가입 성공");

        return postJoinRes;
    }

    @PostMapping("/login")
    public PostLoginRes login(@RequestBody PostLoginReq postLoginReq){
        PostLoginRes postLoginRes = userService.login(postLoginReq);
        return postLoginRes;
    }

}
