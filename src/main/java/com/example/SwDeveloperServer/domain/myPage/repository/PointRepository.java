package com.example.SwDeveloperServer.domain.myPage.repository;

import com.example.SwDeveloperServer.domain.myPage.entity.Point;
import com.example.SwDeveloperServer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByUser(User user);
}
