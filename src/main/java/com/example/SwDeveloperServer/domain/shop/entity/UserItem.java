package com.example.SwDeveloperServer.domain.shop.entity;

import com.example.SwDeveloperServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToOne
    @JoinColumn(name="itemId")
    private Item item;

    private int itemQuantity; // 계속 업데이트 됨
}
