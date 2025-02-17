package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengePostTagRepository extends JpaRepository<ChallengePostTag, Long> {
    List<ChallengePostTag> findByChallengePost(ChallengePost challengePost);
}
