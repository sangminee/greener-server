package com.example.SwDeveloperServer.domain.myPage.repository;

import com.example.SwDeveloperServer.domain.myPage.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
}
