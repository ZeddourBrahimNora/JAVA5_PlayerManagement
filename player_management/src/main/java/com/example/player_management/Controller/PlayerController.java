package com.example.player_management.Controller;

import com.example.player_management.DTO.PlayerDTO;
import com.example.player_management.Service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/joueurs")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Endpoint : GET /joueurs
    // Récupérer la liste de tous les joueurs
    // Endpoint : GET /joueurs
    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }


    // Endpoint : POST /joueurs
    // Ajouter un nouveau joueur
    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.createPlayer(playerDTO));
    }

    // Endpoint : PUT /joueurs/{id}
   // Modifier les informations d'un joueur existant
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        PlayerDTO updatedPlayer = playerService.updatePlayer(id, playerDTO);
        return ResponseEntity.ok(updatedPlayer);
    }

    // Endpoint : DELETE /joueurs/{id}
    // Supprimer un joueur existant de la base de données
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok("Player deleted successfully");
    }

    // Endpoint : GET /joueurs/{id}
    // Obtenir le profil et les statistiques d’un joueur
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        PlayerDTO playerDTO = playerService.getPlayerWithFriends(id);
        return ResponseEntity.ok(playerDTO);
    }
}
