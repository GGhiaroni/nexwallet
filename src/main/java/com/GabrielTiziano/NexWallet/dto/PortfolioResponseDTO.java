package com.GabrielTiziano.NexWallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioResponseDTO {
    private Double totalBalance;
    private List<AssetPortfolioDTO> assets;
}
