package com.example.SwDeveloperServer.domain.user.service;

import com.example.SwDeveloperServer.domain.user.dto.request.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {


    @Test
    public void 회원가입(){
        // given

        // when
        PostJoinReq postJoinReq = new PostJoinReq("test000", "test000"
                , "test000", "test000", "test000", "test000", 0, 0, "USER");
        User user = postJoinReq.toEntity();

        // then

    }
}