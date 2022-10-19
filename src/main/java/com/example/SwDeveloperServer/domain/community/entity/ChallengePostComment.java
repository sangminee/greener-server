package com.example.SwDeveloperServer.domain.community.entity;

import com.example.SwDeveloperServer.domain.community.dto.req.PostCommentReq;
import com.example.SwDeveloperServer.domain.user.entity.User;
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
public class ChallengePostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengePostCommentId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="challengePostId")
    private ChallengePost challengePost;

    private String challengeComment;
    private int challengeGrade; // 별점

    public ChallengePostComment(User user, ChallengePost challengePost,
                                PostCommentReq postCommentReq) {
        this.user = user;
        this.challengePost = challengePost;
        this.challengeComment = postCommentReq.getChallengeComment();
        this.challengeGrade = postCommentReq.getChallengeGrade();
    }
}
