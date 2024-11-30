package com.example.player_management.DAO;

import com.example.player_management.Entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    // rechercher un joueur avec ses amis
    public Player findPlayerWithFriends(Long id) {
        String query = "SELECT p FROM Player p LEFT JOIN FETCH p.friends WHERE p.id = :id";
        return entityManager.createQuery(query, Player.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

