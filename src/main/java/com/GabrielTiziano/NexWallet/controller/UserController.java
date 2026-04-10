package com.GabrielTiziano.NexWallet.controller;

import com.GabrielTiziano.NexWallet.config.TokenService;
import com.GabrielTiziano.NexWallet.dto.*;
import com.GabrielTiziano.NexWallet.model.UserModel;
import com.GabrielTiziano.NexWallet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nexwallet")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDTO));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> userLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserModel user = (UserModel) authentication.getPrincipal();

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/user/{id}/assets")
    public ResponseEntity<UserResponseDTO> addAsset(@PathVariable String id, @RequestBody AssetDTO assetDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAsset(id, assetDTO));
    }

    @GetMapping("/user/{id}/portfolio")
    public ResponseEntity<PortfolioResponseDTO> getUserPortfolio(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserPortfolio(id));
    }
}
