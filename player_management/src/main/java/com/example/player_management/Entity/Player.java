package com.example.player_management.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class Player {

    @Id // Indique la clé primaire d'une entité.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Valeur de l'ID générée automatiquement.
    private long id;
    private String name;
    private 
}
