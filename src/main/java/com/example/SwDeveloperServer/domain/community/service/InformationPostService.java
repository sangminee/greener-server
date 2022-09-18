package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.req.PostInfoReq;
import com.example.SwDeveloperServer.domain.community.dto.res.DeleteResultRes;
import com.example.SwDeveloperServer.domain.community.dto.res.GetInformRes;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.utils.response.BaseException;

import java.util.List;

public interface InformationPostService {
    // 정보글
    PostResultRes setInformationPost(Long userId, PostInfoReq postInfoReq);
    List<GetInformRes> getInformationPosts(Long userId);
    GetInformRes getInformationPost(Long userId, Long informationPostId);

    // 스크랩
    PostResultRes setInformationPostScrap(Long userId, Long informationPostId) throws BaseException;
    DeleteResultRes deleteInformationPostScrap(Long userId, Long informationPostScrapId) throws BaseException;
}
