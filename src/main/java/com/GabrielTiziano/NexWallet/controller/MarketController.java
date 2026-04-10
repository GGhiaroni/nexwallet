package com.GabrielTiziano.NexWallet.controller;

import com.GabrielTiziano.NexWallet.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/market/prices")
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;

    @GetMapping("/{cryptoId}")
    public ResponseEntity<Map<String, Map<String, Double>>> getSpecificPrice(@PathVariable String cryptoId, @RequestParam(required = false) String currency){
        return ResponseEntity.ok(marketService.getMarketPrices(cryptoId, currency));
    }

    @GetMapping
    public ResponseEntity<Map<String, Map<String, Double>>> getMultiplePrices(@RequestParam String assets, @RequestParam(required = false) String currency){
        return ResponseEntity.ok(marketService.getMarketPrices(assets, currency));
    }
}
