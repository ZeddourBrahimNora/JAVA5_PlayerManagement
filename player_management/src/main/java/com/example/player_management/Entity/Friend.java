package com.example.player_management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Génère automatiquement getters, setters, equals, hashCode et toString.
@NoArgsConstructor // Génère un constructeur sans arguments.
@AllArgsConstructor // Génère un constructeur avec tous les arguments.
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-génération de l'ID.
    private Long id;

    @ManyToOne // Relation Many-to-One : plusieurs amis pour un joueur
    @JoinColumn(name = "player_id", nullable = false) // Clé étrangère vers Player
    private Player player;

    @ManyToOne // Relation Many-to-One : désigne un autre joueur comme ami
    @JoinColumn(name = "friend_id", nullable = false) // Clé étrangère vers un autre Player
    private Player friend;

}

