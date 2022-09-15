package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.ChallengeHowToParticipate;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengehowToParticipateRepository extends JpaRepository<ChallengeHowToParticipate, Long> {
    List<ChallengeHowToParticipate> findByChallengePost(ChallengePost challengePost);
}
