package com.example.SwDeveloperServer.domain.community.entity;

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
public class InformationPostScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long informationPostScrapId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="informationPostId")
    private InformationPost informationPost;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="userId")
    private User user;

    private LocalDateTime scrapCreateTime;
    private int state;

    public InformationPostScrap(User user, InformationPost informationPost) {
        this.user = user;
        this.informationPost = informationPost;
        this.scrapCreateTime = LocalDateTime.now();
        this.state = 0;
    }
}
