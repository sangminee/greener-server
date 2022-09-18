package com.example.SwDeveloperServer.domain.community.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
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
public class ChallengePostJoinUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostJoinUserId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private int state;

    public ChallengePostJoinUser(User user, ChallengePost challengePost) {
        this.user = user;
        this.challengePost = challengePost;
        this.state = 0; // 0 :참여, 1 : 참여 x
    }
}
