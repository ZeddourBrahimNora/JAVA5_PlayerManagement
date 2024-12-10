package com.example.player_management.Service;

import com.example.player_management.DAO.PlayerDAO;
import com.example.player_management.DTO.PlayerDTO;
import com.example.player_management.Entity.Player;
import com.example.player_management.Repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerDAO playerDAO;

    public PlayerService(PlayerRepository playerRepository, PlayerDAO playerDAO) {
        this.playerRepository = playerRepository;
        this.playerDAO = playerDAO;
    }

    // Récupérer tous les joueurs
    public List<PlayerDTO> getAllPlayers() {
        // Récupérer tous les joueurs depuis le repository
        List<Player> players = playerRepository.findAll();

        // Convertir chaque joueur en PlayerDTO avec les IDs de leurs amis
        return players.stream().map(player -> {
            List<Long> friendIds = player.getFriends().stream()
                    .map(friend -> friend.getFriend().getId())
                    .collect(Collectors.toList());

            return new PlayerDTO(
                    player.getId(),
                    player.getName(),
                    player.getPseudonyme(),
                    player.getEmail(),
                    player.getLevel(),
                    player.getTotalPoints(),
                    friendIds
            );
        }).collect(Collectors.toList());
    }


    // Créer un joueur
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        // Vérifier si l'utilisateur n'existe pas déjà
        boolean exists = playerRepository.existsByEmailOrPseudonyme(playerDTO.getEmail(), playerDTO.getPseudonyme());
        if (exists) {
            throw new RuntimeException("Player with this email or pseudonyme already exists");
        }
        // crée le nvx joueur
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setPseudonyme(playerDTO.getPseudonyme());
        player.setEmail(playerDTO.getEmail());
        player.setLevel(playerDTO.getLevel());
        player.setTotalPoints(playerDTO.getTotalPoints());
        //sauvegarder le joueur
        Player savedPlayer = playerRepository.save(player);
        return new PlayerDTO(savedPlayer.getId(), savedPlayer.getName(), savedPlayer.getPseudonyme(),
                savedPlayer.getEmail(), savedPlayer.getLevel(), savedPlayer.getTotalPoints());
    }

    // Modifier les informations d'un joueur existant
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        player.setName(playerDTO.getName());
        player.setPseudonyme(playerDTO.getPseudonyme());
        player.setEmail(playerDTO.getEmail());
        player.setLevel(playerDTO.getLevel());
        player.setTotalPoints(playerDTO.getTotalPoints());
        Player updatedPlayer = playerRepository.save(player);
        return new PlayerDTO(updatedPlayer.getId(), updatedPlayer.getName(), updatedPlayer.getPseudonyme(),
                updatedPlayer.getEmail(), updatedPlayer.getLevel(), updatedPlayer.getTotalPoints());
    }

    // Service : Supprimer un joueur existant de la base de données
    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not found");
        }
        playerRepository.deleteById(id);
    }

    // Récupérer un joueur par ID
    // Logique métier : récupérer un joueur avec ses amis et le convertir en DTO
    public PlayerDTO getPlayerWithFriends(Long id) {
        Player player = playerDAO.findPlayerWithFriends(id); // Appel au DAO pour afficher les amis

        // Transformation des amis en une liste d'IDs
        List<Long> friendIds = player.getFriends().stream()
                .map(friend -> friend.getFriend().getId())
                .collect(Collectors.toList());

        // Retourner un DTO au contrôleur
        return new PlayerDTO(player.getId(), player.getName(), player.getPseudonyme(),
                player.getEmail(), player.getLevel(), player.getTotalPoints(), friendIds);
    }
}
