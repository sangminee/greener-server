package com.example.SwDeveloperServer.domain.shop.repository;

import com.example.SwDeveloperServer.domain.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
