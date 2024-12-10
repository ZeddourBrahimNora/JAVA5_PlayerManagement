package com.example.player_management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // Génère automatiquement getters, setters, equals, hashCode et toString.
@NoArgsConstructor // Génère un constructeur sans arguments.
@AllArgsConstructor // Génère un constructeur avec tous les arguments.
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-génération de l'ID.
    private Long id;
    private String name;
    private String pseudonyme;
    private String email;
    private int level;
    private int totalPoints;

    // Relation One-to-Many : un joueur peut avoir plusieurs amis
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true) //mappedBy = "player" indique que la relation est gérée par l'attribut player dans l’entité Friend
    // Liste des joueurs qui ont ajouté ce joueur en tant qu'ami
    private List<Friend> friends = new ArrayList<>(); // Initialisation pour eviter les NullPointerException.


    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friendsWithMe = new ArrayList<>(); // Liste des joueurs qui ont ajouté ce joueur en tant qu'ami



}
