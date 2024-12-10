package com.example.player_management.Service;

import com.example.player_management.Entity.Friend;
import com.example.player_management.Entity.Player;
import com.example.player_management.Repository.FriendRepository;
import com.example.player_management.Repository.PlayerRepository;
import jakarta.transaction.Transactional;
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
                .orElseThrow(() -> new RuntimeException("Player not found with ID : : " + playerId));

        // Récupérer le joueur à ajouter comme ami
        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found with ID : : " + friendId));

        // Vérifie si l'amitié existe déjà
        boolean exists = player.getFriends().stream()
                .anyMatch(f -> f.getFriend().getId().equals(friendId));
        if (exists) {
            throw new RuntimeException("Friendship already exists");
        }

        // Créer la relation d'amitié bidirectionnelle
        Friend friendship = new Friend();
        friendship.setPlayer(player);
        friendship.setFriend(friend);

        Friend reverseFriendship = new Friend();
        reverseFriendship.setPlayer(friend);
        reverseFriendship.setFriend(player);

        // Sauvegarde les deux relations dans les deux sens
        friendRepository.save(friendship);
        friendRepository.save(reverseFriendship);
    }

    @Transactional
    public void removeFriend(Long playerId, Long friendId) {
        // Vérifie que les joueurs existent
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));
        Player friend = playerRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found with ID: " + friendId));

        // Supprime les relations dans les deux sens
        friendRepository.deleteByPlayerAndFriend(player, friend);
        friendRepository.deleteByPlayerAndFriend(friend, player);
    }
}
