package com.GabrielTiziano.NexWallet.controller;

import com.GabrielTiziano.NexWallet.dto.AssetDTO;
import com.GabrielTiziano.NexWallet.dto.UserRequestDTO;
import com.GabrielTiziano.NexWallet.dto.UserResponseDTO;
import com.GabrielTiziano.NexWallet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDTO));
    }

    @PostMapping("/{id}/assets")
    public ResponseEntity<UserResponseDTO> addAsset(@PathVariable String id, @RequestBody AssetDTO assetDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAsset(id, assetDTO));
    }
}
