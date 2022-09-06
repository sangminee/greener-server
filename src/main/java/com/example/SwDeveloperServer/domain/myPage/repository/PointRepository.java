package com.example.SwDeveloperServer.domain.myPage.repository;

import com.example.SwDeveloperServer.domain.myPage.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
