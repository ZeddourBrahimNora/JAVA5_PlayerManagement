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
    private int niveau;
    private int pointsTotaux;
    private List<Long> friendIds;

    public PlayerDTO(Long id, String name, String pseudonyme, String email, int niveau, int pointsTotaux) {
        this.id = id;
        this.name = name;
        this.pseudonyme = pseudonyme;
        this.email = email;
        this.niveau = niveau;
        this.pointsTotaux = pointsTotaux;
        this.friendIds = null; // Par défaut, aucun ami n'est associé
    }
}

