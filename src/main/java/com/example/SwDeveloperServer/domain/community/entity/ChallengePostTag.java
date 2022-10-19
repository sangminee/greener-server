package com.example.SwDeveloperServer.domain.community.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengePostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostTagId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private String tagContent;

    private int state; // 태그 유형 - 0 : 사진인증횟수, 1 : 챌린지 기간

    public ChallengePostTag(ChallengePost challengePost, String content) {
        this.challengePost = challengePost;
        this.tagContent = content;
    }
}
