package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostJoinUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengePostJoinRepository extends JpaRepository<ChallengePostJoinUser, Long> {
    List<ChallengePostJoinUser> findByChallengePost(ChallengePost challengePost);
}
