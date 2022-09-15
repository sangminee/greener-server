package com.example.SwDeveloperServer.domain.community.entity;

import com.example.SwDeveloperServer.domain.community.dto.req.PostChallengeReq;
import com.example.SwDeveloperServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String challengeTitle;
    private LocalDateTime challengeCreatedAt;

    private String challengeTitlePhoto;

    private LocalDateTime toStartDate; // 시작날짜
    private LocalDateTime toEndDate; // 종료 날짜

    private int limitPeople; // 인원제한
    private int successPoint;  // 성공 시 획득 포인트
    private int state; // 0 : 게시물 등록 o, 1 : 게시물 등록  x

    // 예상 CO2 발생량 - 시스템 상에서 계산
    // 예상 소나무 획득그루 - 시스템 상에서 계산

    public ChallengePost(User user, PostChallengeReq postChallengeReq,
                         LocalDateTime getStartDate, LocalDateTime getEndDate) {
        this.user = user;
        this.challengeTitle = postChallengeReq.getChallengeTitle();
        this.challengeCreatedAt = LocalDateTime.now();
        this.challengeTitlePhoto = postChallengeReq.getChallengeTitlePhoto();
        this.toStartDate = getStartDate;
        this.toEndDate = getEndDate;
        this.successPoint = postChallengeReq.getSuccessPoint();
        this.state = 0;
        this.limitPeople = postChallengeReq.getLimitPeople();
    }
}
