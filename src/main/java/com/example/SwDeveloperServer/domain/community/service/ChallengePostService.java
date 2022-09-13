package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.res.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.req.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;

import java.util.List;

public interface ChallengePostService {
    PostResultRes setChallengePost(Long userId, PostChallengeReq postChallenegeReq);

    List<GetChallengeRes> getChallengePosts(Long userId);

    GetChallengeRes getChallengePost(Long userId, Long challengePostId);
}
