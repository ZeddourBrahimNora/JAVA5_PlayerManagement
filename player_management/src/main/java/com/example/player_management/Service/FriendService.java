package com.example.player_management.Service;

import com.example.player_management.Entity.Friend;
import com.example.player_management.Entity.Player;
import com.example.player_management.Repository.FriendRepository;
import com.example.player_management.Repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final PlayerRepository playerRepository;

    public FriendService(FriendRepository friendRepository, PlayerRepository playerRepository) {
        this.friendRepository = friendRepository;
        this.playerRepository = playerRepository;
    }

    // Ajouter un ami à un joueur
    public void addFriend(Long playerId, Long friendId) {
        // Récupérer le joueur principal
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Joueur introuvable avec l'ID : " + playerId));

        // Récupérer le joueur à ajouter comme ami
        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Ami introuvable avec l'ID : " + friendId));

        // Créer la relation d'amitié
        Friend friendship = new Friend();
        friendship.setPlayer(player);
        friendship.setFriend(friend);

        // Sauvegarder la relation
        friendRepository.save(friendship);
    }
}
