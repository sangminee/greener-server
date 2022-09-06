package com.example.SwDeveloperServer.domain.user.service;

import com.example.SwDeveloperServer.domain.user.repository.dto.request.PostJoinReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostFindEmailRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostFindPasswordRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostJoinRes;
import com.example.SwDeveloperServer.domain.user.repository.dto.request.PostLoginReq;
import com.example.SwDeveloperServer.domain.user.repository.dto.response.PostLoginRes;
import com.example.SwDeveloperServer.utils.response.BaseException;

public interface UerService {

    PostJoinRes join(PostJoinReq postJoinReq) throws BaseException;
    PostLoginRes login(PostLoginReq postLoginReq) throws BaseException ;

    PostFindEmailRes findEmail(String phone) throws BaseException;
    PostFindPasswordRes findPassword(String email) throws BaseException;
}
