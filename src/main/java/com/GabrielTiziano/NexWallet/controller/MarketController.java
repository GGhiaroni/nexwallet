package com.GabrielTiziano.NexWallet.controller;

import com.GabrielTiziano.NexWallet.dto.Top10CoinsDTO;
import com.GabrielTiziano.NexWallet.service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
@Tag(name="Market Controller", description = "Recurso responsável por gerenciar os endpoints que retornam o preço de determinado asset e múltipos assets em tempo real e o top 10 no momento da requisição.")
public class MarketController {
    private final MarketService marketService;

    @Operation(summary = "Buscar preço de uma moeda específica", description = "Retorna a cotação atual de uma criptomoeda baseada no seu ID (ex: bitcoin). A moeda de conversão padrão é USD.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Cotação retornada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado/Token inválido."),
            @ApiResponse(responseCode = "404", description = "Criptomoeda não encontrada na base de dados externa.")
    })
    @GetMapping("/prices/{cryptoId}")
    public ResponseEntity<Map<String, Map<String, Double>>> getSpecificPrice(
            @Parameter(description = "ID da criptomoeda (ex: bitcoin, ethereum)", required = true)
            @PathVariable String cryptoId,
            @Parameter(description = "Moeda fiduciária para conversão (ex: usd, brl). Padrão: usd")
            @RequestParam(required = false) String currency
    ){
        return ResponseEntity.ok(marketService.getMarketPrices(cryptoId, currency));
    }

    @Operation(summary = "Buscar preços de múltiplas moedas", description = "Retorna a cotação atual de várias criptomoedas simultaneamente. Excelente para carregar dashboards.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Cotações retornadas com sucesso."),
            @ApiResponse(responseCode = "400", description = "Parâmetros de busca inválidos ou ausentes."),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado/Token inválido.")
    })
    @GetMapping("/prices")
    public ResponseEntity<Map<String, Map<String, Double>>> getMultiplePrices(
            @Parameter(description = "IDs das criptomoedas separados por vírgula (ex: bitcoin,ethereum,solana)", required = true)
            @RequestParam String cryptoIds,
            @Parameter(description = "Moeda fiduciária para conversão (ex: usd, brl). Padrão: usd")
            @RequestParam(required = false) String currency
    ){
        return ResponseEntity.ok(marketService.getMarketPrices(cryptoIds, currency));
    }

    @Operation(summary = "Listar o Top 10 do mercado", description = "Retorna as 10 principais criptomoedas do mercado ordenadas por capitalização (Market Cap).")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Top 10 listado com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autenticado/Token inválido.")
    })
    @GetMapping("/top10")
    public ResponseEntity<List<Top10CoinsDTO>> getTop10Coins(
            @Parameter(description = "Moeda fiduciária para os valores (ex: usd, brl). Padrão: usd")
            @RequestParam(required = false) String currency
    ){
        return ResponseEntity.ok(marketService.getTop10Coins(currency));
    }
}
