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
public class ChallengeHowToParticipate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long howToParticipateId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private String challengeContent;

    public ChallengeHowToParticipate(ChallengePost challengePost, String content) {
        this.challengePost = challengePost;
        this.challengeContent = content;
    }
}
