package com.example.SwDeveloperServer.domain.community.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformationPostScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long informationPostScrapId;

    @ManyToOne
    @JoinColumn(name="informationPostId")
    private InformationPost informationPost;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private LocalDateTime scrapCreateTime;
    private int state;
}
