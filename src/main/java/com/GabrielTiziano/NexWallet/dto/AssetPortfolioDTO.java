package com.GabrielTiziano.NexWallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetPortfolioDTO {
    private String cryptoId;
    private Double quantity;
    private Double currentPrice;
    private Double totalValue;
}
