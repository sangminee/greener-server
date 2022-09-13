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
public class ChallengePostPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostPhotoId;

    @ManyToOne
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private String challengePostPhotoUrl;
}
