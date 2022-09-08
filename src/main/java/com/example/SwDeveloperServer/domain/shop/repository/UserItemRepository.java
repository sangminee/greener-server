package com.example.SwDeveloperServer.domain.shop.repository;

import com.example.SwDeveloperServer.domain.shop.entity.UserItem;
import com.example.SwDeveloperServer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    List<UserItem> findAllByUser(User user);
}
