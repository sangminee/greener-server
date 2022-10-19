package com.example.SwDeveloperServer.domain.community.entity;

import com.example.SwDeveloperServer.domain.community.dto.req.PostInfoReq;
import com.example.SwDeveloperServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformationPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long informationPostId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="userId")
    private User user;

    private String infoTitle;
    private String infoContent;
    private String infoTitlePhoto;
    private LocalDateTime infoCreateTime;
    private LocalDateTime infoUpdateTime;
    private int state;

    public InformationPost(User user, PostInfoReq postInfoReq) {
        this.user = user;
        this.infoTitle = postInfoReq.getInfoTitle();
        this.infoContent = postInfoReq.getInfoTitle();
        this.infoTitlePhoto = postInfoReq.getInfoTitlePhoto();
        this.infoCreateTime = LocalDateTime.now();
        this.state = 0;
    }
}
