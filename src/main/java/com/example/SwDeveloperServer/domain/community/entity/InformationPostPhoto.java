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
public class InformationPostPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long informationPostPhotoId;

    @ManyToOne
    @JoinColumn(name="informationPostId")
    private InformationPost informationPost;

    private String informationPostPhotoUrl;
}
