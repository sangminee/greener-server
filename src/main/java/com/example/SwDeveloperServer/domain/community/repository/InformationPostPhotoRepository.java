package com.example.SwDeveloperServer.domain.community.repository;

import com.example.SwDeveloperServer.domain.community.entity.InformationPost;
import com.example.SwDeveloperServer.domain.community.entity.InformationPostPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationPostPhotoRepository extends JpaRepository<InformationPostPhoto, Long> {
    List<InformationPostPhoto> findByInformationPost(InformationPost informationPost);
}
