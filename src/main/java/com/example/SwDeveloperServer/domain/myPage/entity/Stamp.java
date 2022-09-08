package com.example.SwDeveloperServer.domain.myPage.entity;

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
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stampId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String stampUrl;
}
