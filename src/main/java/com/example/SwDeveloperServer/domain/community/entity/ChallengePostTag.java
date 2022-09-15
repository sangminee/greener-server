package com.example.SwDeveloperServer.domain.community.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengePostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostTagId;

    @ManyToOne
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private String tagContent;

    // 태그 유형 - 사진인증횟수, 챌린지 기간
}
