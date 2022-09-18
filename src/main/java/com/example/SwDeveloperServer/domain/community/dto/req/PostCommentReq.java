package com.example.SwDeveloperServer.domain.community.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentReq {
    private String challengeComment;
    private int challengeGrade; // 별점
}
