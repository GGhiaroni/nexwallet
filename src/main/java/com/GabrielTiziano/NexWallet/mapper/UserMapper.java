package com.GabrielTiziano.NexWallet.mapper;

import com.GabrielTiziano.NexWallet.dto.UserRequestDTO;
import com.GabrielTiziano.NexWallet.dto.UserResponseDTO;
import com.GabrielTiziano.NexWallet.model.UserModel;
import org.springframework.stereotype.Component;

public class UserMapper {
    public static UserModel toModel(UserRequestDTO userRequestDTO){
            return UserModel.builder()
                    .name(userRequestDTO.getName())
                    .email(userRequestDTO.getEmail())
                    .password(userRequestDTO.getPassword())
                    .build();
    }

    public static UserResponseDTO toUserResponseDTO(UserModel userModel){
        return UserResponseDTO.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .assets(userModel.getAssets() != null
                        ? userModel.getAssets().stream().map(AssetMapper::toAssetDTO).toList()
                        : null)
                .build();
    }
}
