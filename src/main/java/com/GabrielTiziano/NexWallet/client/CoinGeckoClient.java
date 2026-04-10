package com.GabrielTiziano.NexWallet.client;

import com.GabrielTiziano.NexWallet.dto.Top10CoinsDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "CoinGeckoClient", url = "${nexwallet.client.coingecko}")
public interface CoinGeckoClient {
    @Cacheable(value = "cotacoes", key = "#cryptoId + '-' + #currency")
    @GetMapping("/simple/price")
    Map<String, Map<String, Double>> getPrice(
            @RequestParam("ids") String cryptoId,
            @RequestParam("vs_currencies") String currency
    );

    @Cacheable(cacheNames = "topCoins", key = "#currency + '-' + #perPage")
    @GetMapping("/coins/markets")
    List<Top10CoinsDTO> getTop10Coins(
            @RequestParam("vs_currency") String currency,
            @RequestParam("order") String order,
            @RequestParam("per_page") Integer perPage,
            @RequestParam("page") Integer page
    );
}
