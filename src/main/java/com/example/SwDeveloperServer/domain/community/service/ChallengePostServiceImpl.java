package com.example.SwDeveloperServer.domain.community.service;

import com.example.SwDeveloperServer.domain.community.dto.GetChallengeRes;
import com.example.SwDeveloperServer.domain.community.dto.PostChallengeReq;
import com.example.SwDeveloperServer.domain.community.dto.PostChallengeRes;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostPhotoRepository;
import com.example.SwDeveloperServer.domain.community.repository.ChallengePostRepository;
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

    public ChallengePostServiceImpl(UserJpaRepository userJpaRepository, ChallengePostRepository challengePostRepository, ChallengePostPhotoRepository challengePostPhotoRepository) {
        this.userJpaRepository = userJpaRepository;
        this.challengePostRepository = challengePostRepository;
        this.challengePostPhotoRepository = challengePostPhotoRepository;
    }

    @Override
    public PostChallengeRes setChallengePost(Long userId, PostChallengeReq postChallenegeReq) {
        Optional<User> user = userJpaRepository.findById(userId);

        ChallengePost challengePost = new ChallengePost();
        challengePost.setUser(user.get());
        challengePost.setChallengeTitle(postChallenegeReq.getChallengeTitle());

        challengePost.setChallengeCreatedAt(LocalDateTime.now());
        challengePost.setChallengeTitlePhoto(postChallenegeReq.getChallengeTitlePhoto());
        challengePost.setChallengePostContent(postChallenegeReq.getChallengePostContent());

        String startDate = postChallenegeReq.getToStartDate()+" 00:00:00";
        String endDate = postChallenegeReq.getToEndDate()+" 23:59:59";

        LocalDateTime getStartDate = LocalDateTime.parse(startDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime getEndDate = LocalDateTime.parse(endDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        challengePost.setToStartDate(getStartDate);
        challengePost.setToEndDate(getEndDate);

        challengePostRepository.save(challengePost);

        return new PostChallengeRes("챌린지가 등록되었습니다.");
    }

    @Override
    public List<GetChallengeRes> getChallengePosts(Long userId) {
        List<ChallengePost> challengePosts = challengePostRepository.findAll();

        List<GetChallengeRes> getChallengeResList = new ArrayList<>();
        for(int i=0; i<challengePosts.size(); i++){
            GetChallengeRes getChallengeRes = new GetChallengeRes();
            getChallengeRes.setUserId(challengePosts.get(i).getUser().getUserId());
            getChallengeRes.setEmail(challengePosts.get(i).getUser().getEmail());
            getChallengeRes.setName(challengePosts.get(i).getUser().getName());

            getChallengeRes.setChallengeTitle(challengePosts.get(i).getChallengeTitle());
            getChallengeRes.setChallengeCreatedAt(challengePosts.get(i).getChallengeCreatedAt());
            getChallengeRes.setChallengeTitlePhoto(challengePosts.get(i).getChallengeTitlePhoto());
            getChallengeRes.setChallengePostContent(challengePosts.get(i).getChallengePostContent());

            getChallengeRes.setToStartDate(challengePosts.get(i).getToStartDate());
            getChallengeRes.setToEndDate(challengePosts.get(i).getToEndDate());

            getChallengeResList.add(getChallengeRes);
        }

        return getChallengeResList;
    }

    @Override
    public GetChallengeRes getChallengePost(Long userId, Long challengePostId) {
        Optional<ChallengePost> challengePost = challengePostRepository.findById(challengePostId);

        GetChallengeRes getChallengeRes = new GetChallengeRes();
        getChallengeRes.setUserId(challengePost.get().getUser().getUserId());
        getChallengeRes.setEmail(challengePost.get().getUser().getEmail());
        getChallengeRes.setName(challengePost.get().getUser().getName());

        getChallengeRes.setChallengeTitle(challengePost.get().getChallengeTitle());
        getChallengeRes.setChallengeCreatedAt(challengePost.get().getChallengeCreatedAt());
        getChallengeRes.setChallengeTitlePhoto(challengePost.get().getChallengeTitlePhoto());
        getChallengeRes.setChallengePostContent(challengePost.get().getChallengePostContent());

        getChallengeRes.setToStartDate(challengePost.get().getToStartDate());
        getChallengeRes.setToEndDate(challengePost.get().getToEndDate());

        return getChallengeRes;
    }
}
