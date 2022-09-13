package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.ChallengePost;
import com.example.SwDeveloperServer.domain.community.entity.InformationPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationPostRepository extends JpaRepository<InformationPost, Long> {
}
