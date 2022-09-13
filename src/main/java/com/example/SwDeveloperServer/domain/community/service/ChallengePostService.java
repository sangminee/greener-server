package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.PostChallengeRes;

import java.util.List;

public interface ChallengePostService {
    PostChallengeRes setChallengePost(Long userId, PostChallengeReq postChallenegeReq);

    List<GetChallengeRes> getChallengePosts(Long userId);

    GetChallengeRes getChallengePost(Long userId, Long challengePostId);
}
