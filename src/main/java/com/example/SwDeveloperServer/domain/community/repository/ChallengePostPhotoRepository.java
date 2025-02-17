package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.ChallengePostPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengePostPhotoRepository extends JpaRepository<ChallengePostPhoto, Long> {
    List<ChallengePostPhoto> findByChallengePost(ChallengePost challengePost);
}
