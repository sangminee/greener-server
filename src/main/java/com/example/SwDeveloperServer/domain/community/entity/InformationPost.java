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
public class InformationPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long informationPostId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String infoTitle;
    private String infoContent;
    private String infoTitlePhoto;
    private LocalDateTime infoCreateTime;
    private LocalDateTime infoUpdateTime;
    private int state;

    public InformationPost(User user, String infoTitle, String infoContent,
                           String infoTitlePhoto, LocalDateTime now, int state) {
        this.user = user;
        this.infoContent = infoContent;
        this.infoTitlePhoto = infoTitlePhoto;
        this.infoCreateTime = now;
        this.state = state;
    }
}
