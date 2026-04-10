package com.GabrielTiziano.NexWallet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "CoinGeckoClient", url = "${nexwallet.client.coingecko}")
public interface CoinGeckoClient {
    @GetMapping("/simple/price")
    Map<String, Map<String, Double>> getPrice(
            @RequestParam("ids") String cryptoId,
            @RequestParam("vs_currencies") String currency
    );
}
