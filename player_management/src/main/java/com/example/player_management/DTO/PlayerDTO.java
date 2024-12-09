package com.example.player_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private String pseudonyme;
    private String email;
    private int level;
    private int totalPoints;
    private List<Long> friendIds;

    public PlayerDTO(Long id, String name, String pseudonyme, String email, int level, int totalPoints) {
        this.id = id;
        this.name = name;
        this.pseudonyme = pseudonyme;
        this.email = email;
        this.level = level;
        this.totalPoints = totalPoints;
        this.friendIds = null; // Par défaut aucun ami n'est associé
    }
}

