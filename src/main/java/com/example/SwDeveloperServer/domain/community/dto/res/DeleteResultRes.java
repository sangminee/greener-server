package com.example.SwDeveloperServer.domain.community.dto.res;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResultRes {
    private Long id;
    private String message;

    public DeleteResultRes(Long id) {
        this.id = id;
        this.message = "삭제되었습니다.";
    }
}
