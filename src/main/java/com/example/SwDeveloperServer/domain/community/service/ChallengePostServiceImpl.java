package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.res.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.req.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.res.PostResultRes;
import com.example.SwDeveloperServer.domain.community.entity.ChallengeHowToParticipate;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostPhoto;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostTag;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostPhotoRepository;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostRepository;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostTagRepository;
import com.example.SwDeveloperServer.domain.community.repository.ChallengehowToParticipateRepository;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengePostServiceImpl implements ChallengePostService{

    private final UserJpaRepository userJpaRepository;
    private final ChallengePostRepository challengePostRepository;
    private final ChallengePostPhotoRepository challengePostPhotoRepository;
    private final ChallengePostTagRepository challengePostTagRepository;
    private final ChallengehowToParticipateRepository howToParticipateRepository;

    public ChallengePostServiceImpl(UserJpaRepository userJpaRepository, ChallengePostRepository challengePostRepository,
                                    ChallengePostPhotoRepository challengePostPhotoRepository, ChallengePostTagRepository challengePostTagRepository, ChallengehowToParticipateRepository howToParticipateRepository) {
        this.userJpaRepository = userJpaRepository;
        this.challengePostRepository = challengePostRepository;
        this.challengePostPhotoRepository = challengePostPhotoRepository;
        this.challengePostTagRepository = challengePostTagRepository;
        this.howToParticipateRepository = howToParticipateRepository;
    }

    @Override
    @Transactional
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
        int howToSize = postChallengeReq.getGetHowTo().size();
        if(howToSize != 0){
            for(int i=0; i<howToSize; i++){
                ChallengeHowToParticipate challengeHowToParticipate
                        = new ChallengeHowToParticipate(challengePost, postChallengeReq.getGetHowTo().poll());
                howToParticipateRepository.save(challengeHowToParticipate);
            }
        }
        // 사진
        int photoSize = postChallengeReq.getGetPhoto().size();
        if(photoSize != 0){
            for(int i=0; i<photoSize; i++){
                ChallengePostPhoto challengePostPhoto
                        = new ChallengePostPhoto(challengePost,postChallengeReq.getGetPhoto().poll());
                challengePostPhotoRepository.save(challengePostPhoto);
            }
        }
        // 태그
        int tagSize = postChallengeReq.getGetTag().size();
        if(tagSize != 0){
            for(int i=0; i<tagSize; i++){
                ChallengePostTag challengePostTag
                        = new ChallengePostTag(challengePost,postChallengeReq.getGetTag().poll());
                challengePostTagRepository.save(challengePostTag);
            }
        }

        return new PostResultRes(challengePost.getChallengePostId(),"챌린지가 등록되었습니다.");
    }

    public List<String> getPhotos(ChallengePost challengePost){
        List<String> getPhotos = new LinkedList<>();
        List<ChallengePostPhoto> getList
                = challengePostPhotoRepository.findByChallengePost(challengePost);
        int size = getList.size();
        if(size != 0){
            for(int i=0; i<size; i++){
                getPhotos.add(getList.get(i).getChallengePostPhotoUrl());
            }
        }
        return getPhotos;
    }
    public List<String> getGetHowTo(ChallengePost challengePost){
        List<String> getGetHowTo = new LinkedList<>();
        List<ChallengeHowToParticipate> getList
                =howToParticipateRepository.findByChallengePost(challengePost);
        int size = getList.size();
        if(size != 0){
            for(int i=0; i<size; i++){
                getGetHowTo.add(getList.get(i).getChallengeContent());
            }
        }
        return getGetHowTo;
    }
    public List<String> getTags(ChallengePost challengePost){
        List<String> getTags = new LinkedList<>();
        List<ChallengePostTag> getList
                =challengePostTagRepository.findByChallengePost(challengePost);
        int size = getList.size();
        if(size != 0){
            for(int i=0; i<size; i++){
                getTags.add(getList.get(i).getTagContent());
            }
        }
        return getTags;
    }

    @Override
    public List<GetChallengeRes> getChallengePosts(Long userId) {
        List<ChallengePost> challengePosts = challengePostRepository.findAll();
        List<GetChallengeRes> getChallengeResList = new ArrayList<>();
        for(int i=0; i<challengePosts.size(); i++){
            List<String> getPhotos = getPhotos(challengePosts.get(i));
            List<String> getGetHowTo = getGetHowTo(challengePosts.get(i));
            List<String> getTags = getTags(challengePosts.get(i));
            GetChallengeRes getChallengeRes = new GetChallengeRes(challengePosts.get(i),
                    getPhotos, getGetHowTo, getTags);
            getChallengeResList.add(getChallengeRes);
        }
        return getChallengeResList;
    }

    // 챌린지 후기(댓글) 추가
    @Override
    public GetChallengeRes getChallengePost(Long userId, Long challengePostId) {
        Optional<ChallengePost> challengePost = challengePostRepository.findById(challengePostId);
        List<String> getPhotos = getPhotos(challengePost.get());
        List<String> getGetHowTo = getGetHowTo(challengePost.get());
        List<String> getTags = getTags(challengePost.get());
        GetChallengeRes getChallengeRes = new GetChallengeRes(challengePost.get(),
                getPhotos, getGetHowTo, getTags);
        return getChallengeRes;
    }

}
