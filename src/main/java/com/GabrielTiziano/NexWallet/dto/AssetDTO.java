package com.GabrielTiziano.NexWallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {
    private String cryptoId;
    private Double quantity;
}
