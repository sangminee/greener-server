package com.example.SwDeveloperServer.domain.community.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
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
public class HowToParticipate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long howToParticipateId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private String challengeContent;
}
