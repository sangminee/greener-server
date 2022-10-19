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
public class ChallengePostPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostPhotoId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private String challengePostPhotoUrl;

    public ChallengePostPhoto(ChallengePost challengePost, String url) {
        this.challengePost = challengePost;
        this.challengePostPhotoUrl = url;
    }
}
