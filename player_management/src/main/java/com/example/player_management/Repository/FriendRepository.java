package com.example.player_management.Repository;

import com.example.player_management.Entity.Friend;
import com.example.player_management.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    void deleteByPlayerAndFriend(Player player, Player friend);// JPA se charge du code

}

