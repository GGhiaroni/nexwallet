package com.GabrielTiziano.NexWallet.mapper;

import com.GabrielTiziano.NexWallet.dto.AssetDTO;
import com.GabrielTiziano.NexWallet.model.AssetModel;
import org.springframework.stereotype.Component;

public class AssetMapper {
    public static AssetModel toModel(AssetDTO assetDTO){
        return AssetModel.builder()
                .cryptoId(assetDTO.getCryptoId())
                .quantity(assetDTO.getQuantity())
                .build();
    }

    public static AssetDTO toAssetDTO(AssetModel assetModel){
        return AssetDTO.builder()
                .cryptoId(assetModel.getCryptoId())
                .quantity(assetModel.getQuantity())
                .build();
    }
}
