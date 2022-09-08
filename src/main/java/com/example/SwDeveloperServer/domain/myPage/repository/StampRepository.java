package com.example.SwDeveloperServer.domain.myPage.repository;

import com.example.SwDeveloperServer.domain.myPage.entity.Stamp;
import com.example.SwDeveloperServer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findAllByUser(User user);
}
