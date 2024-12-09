package com.example.player_management.Controller;

import com.example.player_management.DTO.FriendDTO;
import com.example.player_management.Service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joueurs/{playerId}/amis")
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // Endpoint : POST /joueurs/{id}/amis
    // Ajouter un ami pour un joueur
    @PostMapping
    public ResponseEntity<String> addFriend(@PathVariable Long playerId, @RequestBody FriendDTO friendDTO) {
        friendService.addFriend(playerId, friendDTO.getFriendId());
        return ResponseEntity.ok("Friend added successfully !");
    }
}
