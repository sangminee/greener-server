package com.example.SwDeveloperServer.domain.user.service;

import com.example.SwDeveloperServer.domain.user.dto.request.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.dto.response.PostFindEmailRes;
import com.example.SwDeveloperServer.domain.user.dto.response.PostFindPasswordRes;
import com.example.SwDeveloperServer.domain.user.dto.response.PostJoinRes;
import com.example.SwDeveloperServer.domain.user.dto.request.PostLoginReq;
import com.example.SwDeveloperServer.domain.user.dto.response.PostLoginRes;
import com.example.SwDeveloperServer.utils.response.BaseException;

public interface UerService {

    PostJoinRes join(PostJoinReq postJoinReq) throws BaseException;
    PostLoginRes login(PostLoginReq postLoginReq) throws BaseException ;

    PostFindEmailRes findEmail(String phone) throws BaseException;
    PostFindPasswordRes findPassword(String email) throws BaseException;
}
