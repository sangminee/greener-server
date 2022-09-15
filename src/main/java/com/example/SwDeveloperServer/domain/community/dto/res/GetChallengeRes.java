package com.example.SwDeveloperServer.domain.community.dto.res;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetChallengeRes {

    // 챌린지 작성자 정보
    private Long userId;
    private String email;
    private String name;

    // 챌린지 정보
    private String challengeTitle;
    private LocalDateTime challengeCreatedAt;
    private String challengeTitlePhoto;

    private int successPoint;
    private int limitPeople; // 인원제한
    private LocalDateTime toStartDate;
    private LocalDateTime toEndDate;

    // 챌린지 정보 사진
    private List<String> photos;
    // 챌린지 방법
    private List<String> howTo;
    // 챌린지 태그
    private List<String> tags;


    // 챌린지 후기 정보

    // 현재 참여하고 있는 유저

    public GetChallengeRes(ChallengePost challengePost, List<String> getPhotos, List<String> getGetHowTo, List<String> getTags){
        this.userId = challengePost.getUser().getUserId();
        this.email = challengePost.getUser().getEmail();
        this.name = challengePost.getUser().getName();
        this.challengeTitle = challengePost.getChallengeTitle();
        this.challengeCreatedAt=challengePost.getChallengeCreatedAt();
        this.challengeTitlePhoto=challengePost.getChallengeTitlePhoto();
        this.successPoint=challengePost.getSuccessPoint();
        this.limitPeople =challengePost.getLimitPeople();
        this.toStartDate=challengePost.getToStartDate();
        this.toEndDate=challengePost.getToEndDate();

        this.photos = getPhotos;
        this.howTo = getGetHowTo;
        this.tags = getTags;
    }
}
