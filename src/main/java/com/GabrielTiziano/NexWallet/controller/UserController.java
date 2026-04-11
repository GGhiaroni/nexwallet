package com.GabrielTiziano.NexWallet.controller;

import com.GabrielTiziano.NexWallet.config.TokenService;
import com.GabrielTiziano.NexWallet.dto.*;
import com.GabrielTiziano.NexWallet.model.UserModel;
import com.GabrielTiziano.NexWallet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name="User Controller", description = "Recurso responsável por gerenciar os endpoints referentes ao user: cadastrar um novo usuário, realizar o login, adicionar um asset à carteira e ver o portfólio inteiro.")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Operation(summary = "Cria um novo usuário", description = "Rota cria um novo usuário no banco de dados.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação do usuário.")
    })
    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDTO));
    }

    @Operation(summary = "Realiza o login de um usuário cadastrado", description = "Rota realiza o login de um usuário cadastrado no banco de dados.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso. Gera um token JWT e devolve ao usuário na resposta da requisição."),
            @ApiResponse(responseCode = "400", description = "Formato de requisição inválido."),
            @ApiResponse(responseCode = "401", description = "Credenciais incorretas (Não autorizado).")
    })
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> userLogin(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserModel user = (UserModel) authentication.getPrincipal();

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Rota responsável por adicionar um asset", description = "Adiciona um asset à carteira do usuário cujo id é fornecido via PathVariable.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Asset adicionado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados do asset inválidos."),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado/Token inválido."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @PostMapping("/user/{id}/assets")
    public ResponseEntity<UserResponseDTO> addAsset(@PathVariable String id, @Valid @RequestBody AssetDTO assetDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAsset(id, assetDTO));
    }

    @Operation(summary = "Rota responsável por listar toda a carteira/portfolio do usuario", description = "Lista toda a carteira do usuário cujo id é fornecido via PathVariable.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Carteira/Portfolio listado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado.")
    })
    @GetMapping("/user/{id}/portfolio")
    public ResponseEntity<PortfolioResponseDTO> getUserPortfolio(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserPortfolio(id));
    }
}
