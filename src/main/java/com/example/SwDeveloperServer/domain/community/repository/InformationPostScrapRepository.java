package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.InformationPost;
import com.example.SwDeveloperServer.domain.community.entity.InformationPostScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationPostScrapRepository extends JpaRepository<InformationPostScrap, Long> {
    List<InformationPostScrap> findByInformationPost(InformationPost informationPost);
}
