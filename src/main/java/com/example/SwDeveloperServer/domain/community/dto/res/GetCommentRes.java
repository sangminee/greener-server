package com.example.SwDeveloperServer.domain.community.dto.res;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentRes {
    private Long userId;
    private String nickname;

    private long challengePostCommentId;
    private String challengeComment;
    private int challengeGrade; // 별점

    public GetCommentRes(ChallengePostComment challengePostComment) {
        this.userId = challengePostComment.getUser().getUserId();
        this.nickname = challengePostComment.getUser().getNickname();

        this.challengePostCommentId = challengePostComment.getChallengePostCommentId();
        this.challengeComment = challengePostComment.getChallengeComment();
        this.challengeGrade = challengePostComment.getChallengeGrade();
    }
}
