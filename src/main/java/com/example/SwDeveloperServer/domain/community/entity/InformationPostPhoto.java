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
public class InformationPostPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long informationPostPhotoId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="informationPostId")
    private InformationPost informationPost;

    private String informationPostPhotoUrl;

    public InformationPostPhoto(InformationPost informationPost, String url) {
        this.informationPost = informationPost;
        this.informationPostPhotoUrl = url;
    }

}
