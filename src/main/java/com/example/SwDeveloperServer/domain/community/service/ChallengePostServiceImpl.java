package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.req.*;
import com.example.SwDeveloperServer.domain.community.dto.res.*;
import com.example.SwDeveloperServer.domain.community.entity.*;
import com.example.SwDeveloperServer.domain.community.repository.*;
import com.example.SwDeveloperServer.domain.user.entity.User;
import com.example.SwDeveloperServer.domain.user.repository.UserJpaRepository;
import com.example.SwDeveloperServer.utils.response.BaseException;
import com.example.SwDeveloperServer.utils.response.ErrorStatus;
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
    private final ChallengePostCommentRepository challengePostCommentRepository;
    private final ChallengePostJoinRepository challengePostJoinRepository;

    public ChallengePostServiceImpl(UserJpaRepository userJpaRepository, ChallengePostRepository challengePostRepository,
                                    ChallengePostPhotoRepository challengePostPhotoRepository, ChallengePostTagRepository challengePostTagRepository, ChallengehowToParticipateRepository howToParticipateRepository, ChallengePostCommentRepository challengePostCommentRepository, ChallengePostJoinRepository challengePostJoinRepository) {
        this.userJpaRepository = userJpaRepository;
        this.challengePostRepository = challengePostRepository;
        this.challengePostPhotoRepository = challengePostPhotoRepository;
        this.challengePostTagRepository = challengePostTagRepository;
        this.howToParticipateRepository = howToParticipateRepository;
        this.challengePostCommentRepository = challengePostCommentRepository;
        this.challengePostJoinRepository = challengePostJoinRepository;
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
    public List<GetCommentRes> getComments(ChallengePost challengePost){
        List<GetCommentRes> getComments = new LinkedList<>();
        List<ChallengePostComment> getList
                =challengePostCommentRepository.findByChallengePost(challengePost);
        int size = getList.size();
        if(size != 0){
            for(int i=0; i<size; i++){
                GetCommentRes getCommentRes
                        = new GetCommentRes(getList.get(i));
                getComments.add(getCommentRes);
            }
        }
        return getComments;
    }

    @Override
    public List<GetChallengeRes> getChallengePosts() {
        List<ChallengePost> challengePosts = challengePostRepository.findAll();
        List<GetChallengeRes> getChallengeResList = new ArrayList<>();
        for(int i=0; i<challengePosts.size(); i++){
            List<String> getPhotos = getPhotos(challengePosts.get(i));
            List<String> getGetHowTo = getGetHowTo(challengePosts.get(i));
            List<String> getTags = getTags(challengePosts.get(i));
            List<GetCommentRes> getComments = getComments(challengePosts.get(i));
            GetChallengeRes getChallengeRes = new GetChallengeRes(challengePosts.get(i),
                    getPhotos, getGetHowTo, getTags, getComments);
            getChallengeResList.add(getChallengeRes);
        }
        return getChallengeResList;
    }

    // 챌린지 후기(댓글) 추가
    @Override
    public GetChallengeRes getChallengePost(Long challengePostId) {
        Optional<ChallengePost> challengePost = challengePostRepository.findById(challengePostId);
        List<String> getPhotos = getPhotos(challengePost.get());
        List<String> getGetHowTo = getGetHowTo(challengePost.get());
        List<String> getTags = getTags(challengePost.get());
        List<GetCommentRes> getComments = getComments(challengePost.get());
        GetChallengeRes getChallengeRes = new GetChallengeRes(challengePost.get(),
                getPhotos, getGetHowTo, getTags, getComments);
        return getChallengeRes;
    }

    @Override
    public GetChallengeRes setChallengeComment(Long userId, Long challengePostId, PostCommentReq postCommentReq) {
       Optional<User> user = userJpaRepository.findById(userId);
       Optional<ChallengePost> challengePost = challengePostRepository.findById(challengePostId);

        ChallengePostComment challengePostComment
                = new ChallengePostComment(user.get(),challengePost.get(),postCommentReq);
        challengePostCommentRepository.save(challengePostComment);
        return getChallengePost(challengePostId);
    }

    @Override
    public List<GetCommentRes> getChallengeComments(Long userId, Long challengePostId) throws BaseException {
        Optional<ChallengePost> challengePost = challengePostRepository.findById(challengePostId);
        if(challengePost.isEmpty()){
            throw new BaseException(ErrorStatus.INVALID_CHALLENGE_POST);
        }
        return getComments(challengePost.get());
    }

    @Override
    public GetCommentRes getChallengeComment(Long userId, Long challengePostCommentId) throws BaseException {
        Optional<ChallengePostComment> challengePostComment = challengePostCommentRepository.findByChallengePostCommentId(challengePostCommentId);
        if(challengePostComment.isEmpty()){
            throw new BaseException(ErrorStatus.INVALID_CHALLENGE_POST_COMMENT);
        }
        return new GetCommentRes(challengePostComment.get());
    }

    @Override
    public DeleteResultRes deleteChallengeComment(Long userId, Long challengePostCommentId) throws BaseException {
        Optional<ChallengePostComment> challengePostComment = challengePostCommentRepository.findByChallengePostCommentId(challengePostCommentId);
        if(challengePostComment.isEmpty()){
            throw new BaseException(ErrorStatus.INVALID_CHALLENGE_POST_COMMENT);
        }
        if(challengePostComment.get().getUser().getUserId() != userId){
            throw new BaseException(ErrorStatus.INVALID_CHALLENGE_POST_COMMENT_USER);
        }
        DeleteResultRes deleteResultRes = new DeleteResultRes(challengePostComment.get().getChallengePostCommentId());
        challengePostCommentRepository.delete(challengePostComment.get());
        return deleteResultRes;
    }

    @Override
    public PostResultRes setChallengeJoin(Long userId, Long challengePostId) throws BaseException {
        Optional<User> user = userJpaRepository.findById(userId);
        Optional<ChallengePost> challengePost = challengePostRepository.findById(challengePostId);
        if(challengePost.isEmpty()){
            throw new BaseException(ErrorStatus.INVALID_CHALLENGE_POST);
        }
        List<ChallengePostJoinUser> getJoinUserList = challengePostJoinRepository.findByChallengePost(challengePost.get());
        for(int i=0; i<getJoinUserList.size(); i++){
            if(getJoinUserList.get(i).getUser().equals(user.get())){
                throw new BaseException(ErrorStatus.EXIST_CHALLENGE_POST_JOIN_USER);
            }
        }
        ChallengePostJoinUser challengePostJoinUser = new ChallengePostJoinUser(user.get(), challengePost.get());
        challengePostJoinRepository.save(challengePostJoinUser);
        return new PostResultRes(challengePostJoinUser.getChallengePostJoinUserId(), "챌린지 신청이 완료되었습니다.");
    }

}
