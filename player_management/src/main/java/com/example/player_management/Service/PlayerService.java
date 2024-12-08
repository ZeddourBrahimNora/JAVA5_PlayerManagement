package com.example.player_management.Service;

import com.example.player_management.DTO.PlayerDTO;
import com.example.player_management.Entity.Player;
import com.example.player_management.Repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Créer un joueur
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        //verifier si l'user existe pas deja
        boolean exists = playerRepository.existsByEmailOrPseudonyme(playerDTO.getEmail(), playerDTO.getPseudonyme());
        if (exists) {
            throw new RuntimeException("Player with this email or pseudonyme already exists");
        }
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setPseudonyme(playerDTO.getPseudonyme());
        player.setEmail(playerDTO.getEmail());
        player.setNiveau(playerDTO.getNiveau());
        player.setPointsTotaux(playerDTO.getPointsTotaux());
        Player savedPlayer = playerRepository.save(player);
        return new PlayerDTO(savedPlayer.getId(), savedPlayer.getName(), savedPlayer.getPseudonyme(),
                savedPlayer.getEmail(), savedPlayer.getNiveau(), savedPlayer.getPointsTotaux());
    }

    // Modifier les informations d'un joueur existant
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        player.setName(playerDTO.getName());
        player.setPseudonyme(playerDTO.getPseudonyme());
        player.setEmail(playerDTO.getEmail());
        player.setNiveau(playerDTO.getNiveau());
        player.setPointsTotaux(playerDTO.getPointsTotaux());
        Player updatedPlayer = playerRepository.save(player);
        return new PlayerDTO(updatedPlayer.getId(), updatedPlayer.getName(), updatedPlayer.getPseudonyme(),
                updatedPlayer.getEmail(), updatedPlayer.getNiveau(), updatedPlayer.getPointsTotaux());
    }

    // Service : Supprimer un joueur existant de la base de données
    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not found");
        }
        playerRepository.deleteById(id);
    }


    // Récupérer un joueur par ID
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Joueur introuvable avec l'ID : " + id));
        return new PlayerDTO(player.getId(), player.getName(), player.getPseudonyme(),
                player.getEmail(), player.getNiveau(), player.getPointsTotaux());
    }
}
