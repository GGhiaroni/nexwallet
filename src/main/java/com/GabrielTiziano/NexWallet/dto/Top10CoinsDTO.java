package com.GabrielTiziano.NexWallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Top10CoinsDTO implements Serializable {
    private String id;
    private String name;
    private String symbol;
    @JsonProperty("current_price")
    private Double currentPrice;
    @JsonProperty("market_cap_rank")
    private Long marketCapRank;
}
