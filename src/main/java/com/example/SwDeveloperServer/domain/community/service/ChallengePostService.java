package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.req.PostCommentReq;
import com.example.SwDeveloperServer.domain.community.dto.res.DeleteResultRes;
import com.example.SwDeveloperServer.domain.community.dto.res.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.req.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.res.GetCommentRes;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.utils.response.BaseException;

import java.util.List;

public interface ChallengePostService {
    // 챌린지
    PostResultRes setChallengePost(Long userId, PostChallengeReq postChallenegeReq);
    List<GetChallengeRes> getChallengePosts();
    GetChallengeRes getChallengePost(Long challengePostId);

    // 후기
    GetChallengeRes setChallengeComment(Long userId, Long challengePostId, PostCommentReq postCommentReq);
    List<GetCommentRes> getChallengeComments(Long userId, Long challengePostId) throws BaseException;
    GetCommentRes getChallengeComment(Long userId, Long challengePostCommentId) throws BaseException;
    DeleteResultRes deleteChallengeComment(Long userId, Long challengePostCommentId) throws BaseException;

    // 챌린지 참여
    PostResultRes setChallengeJoin(Long userId, Long challengePostId) throws BaseException;
}
