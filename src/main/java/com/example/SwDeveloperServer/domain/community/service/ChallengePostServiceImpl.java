package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.res.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.req.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostPhotoRepository;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostRepository;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostTagRepository;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengePostServiceImpl implements ChallengePostService{

    private final UserJpaRepository userJpaRepository;
    private final ChallengePostRepository challengePostRepository;
    private final ChallengePostPhotoRepository challengePostPhotoRepository;
    private final ChallengePostTagRepository challengePostTagRepository;

    public ChallengePostServiceImpl(UserJpaRepository userJpaRepository, ChallengePostRepository challengePostRepository,
                                    ChallengePostPhotoRepository challengePostPhotoRepository, ChallengePostTagRepository challengePostTagRepository) {
        this.userJpaRepository = userJpaRepository;
        this.challengePostRepository = challengePostRepository;
        this.challengePostPhotoRepository = challengePostPhotoRepository;
        this.challengePostTagRepository = challengePostTagRepository;
    }

    // 사진, 설명 방법, 태그 추가하기
    @Override
    public PostResultRes setChallengePost(Long userId, PostChallengeReq postChallengeReq) {
        Optional<User> user = userJpaRepository.findById(userId);
        String startDate = postChallengeReq.getToStartDate()+" 00:00:00";
        String endDate = postChallengeReq.getToEndDate()+" 23:59:59";
        LocalDateTime getStartDate = LocalDateTime.parse(startDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime getEndDate = LocalDateTime.parse(endDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ChallengePost challengePost = new ChallengePost(user.get(), postChallengeReq,getStartDate,getEndDate);
        challengePostRepository.save(challengePost);

        // 설명 방법
        // 사진
        // 태그

        return new PostResultRes(challengePost.getChallengePostId(),"챌린지가 등록되었습니다.");
    }

    @Override
    public List<GetChallengeRes> getChallengePosts(Long userId) {
        List<ChallengePost> challengePosts = challengePostRepository.findAll();
        List<GetChallengeRes> getChallengeResList = new ArrayList<>();
        for(int i=0; i<challengePosts.size(); i++){
            GetChallengeRes getChallengeRes = new GetChallengeRes(challengePosts.get(i));
            getChallengeResList.add(getChallengeRes);
        }
        return getChallengeResList;
    }

    // 챌린지 후기(댓글) 추가
    @Override
    public GetChallengeRes getChallengePost(Long userId, Long challengePostId) {
        Optional<ChallengePost> challengePost = challengePostRepository.findById(challengePostId);
        GetChallengeRes getChallengeRes = new GetChallengeRes(challengePost.get());
        return getChallengeRes;
    }

}
