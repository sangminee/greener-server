package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.req.PostInfoReq;
import com.example.SwDeveloperServer.domain.community.dto.res.GetInformRes;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;

import java.util.List;

public interface InformationPostService {
    PostResultRes setInformationPost(Long userId, PostInfoReq postInfoReq);

    List<GetInformRes> getInformationPosts(Long userId);

    GetInformRes getInformationPost(Long userId, Long informationPostId);
}
