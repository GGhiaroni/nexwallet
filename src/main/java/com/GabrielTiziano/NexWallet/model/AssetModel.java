package com.GabrielTiziano.NexWallet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetModel {

    private String cryptoId;

    private Double quantity;

}
