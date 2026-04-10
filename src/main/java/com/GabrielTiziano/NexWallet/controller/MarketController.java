package com.GabrielTiziano.NexWallet.controller;

import com.GabrielTiziano.NexWallet.dto.Top10CoinsDTO;
import com.GabrielTiziano.NexWallet.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;

    @GetMapping("/prices/{cryptoId}")
    public ResponseEntity<Map<String, Map<String, Double>>> getSpecificPrice(@PathVariable String cryptoId, @RequestParam(required = false) String currency){
        return ResponseEntity.ok(marketService.getMarketPrices(cryptoId, currency));
    }

    @GetMapping("/prices")
    public ResponseEntity<Map<String, Map<String, Double>>> getMultiplePrices(@RequestParam String cryptoIds, @RequestParam(required = false) String currency){
        return ResponseEntity.ok(marketService.getMarketPrices(cryptoIds, currency));
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Top10CoinsDTO>> getTop10Coins(@RequestParam(required = false) String currency){
        return ResponseEntity.ok(marketService.getTop10Coins(currency));
    }
}
