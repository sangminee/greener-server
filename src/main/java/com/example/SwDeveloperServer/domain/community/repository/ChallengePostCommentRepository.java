package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostComment;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengePostCommentRepository extends JpaRepository<ChallengePostComment, Long> {
    List<ChallengePostComment> findByChallengePost(ChallengePost challengePost);

    Optional<ChallengePostComment> findByChallengePostCommentId(Long challengePostCommentId);
}
