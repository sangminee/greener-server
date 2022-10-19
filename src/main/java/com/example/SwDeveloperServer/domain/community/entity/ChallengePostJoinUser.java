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
public class ChallengePostJoinUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostJoinUserId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private int state;

    public ChallengePostJoinUser(User user, ChallengePost challengePost) {
        this.user = user;
        this.challengePost = challengePost;
        this.state = 0; // 0 : 진행중인 챌린지, 1 : 진행 종료된 챌린지
    }
}
