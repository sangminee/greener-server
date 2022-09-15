package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengePostTagRepository extends JpaRepository<ChallengePostTag, Long> {
}
