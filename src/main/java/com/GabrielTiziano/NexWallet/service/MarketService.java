package com.GabrielTiziano.NexWallet.service;

import com.GabrielTiziano.NexWallet.client.CoinGeckoClient;
import com.GabrielTiziano.NexWallet.dto.Top10CoinsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final CoinGeckoClient coinGeckoClient;

    public Map<String, Map<String, Double>> getMarketPrices(String cryptoIds, String currency){
        String safeCurrency = (currency != null && !currency.isBlank())
                ? currency.toLowerCase()
                : "usd";

        return coinGeckoClient.getPrice(cryptoIds, safeCurrency);
    }

    public List<Top10CoinsDTO> getTop10Coins(String currency){
        String safeCurrency = (currency != null && !currency.isBlank())
                ? currency.toLowerCase()
                : "usd";
        return coinGeckoClient.getTop10Coins(safeCurrency, "market_cap_desc", 10, 1);
    }
}
