package com.GabrielTiziano.NexWallet.service;

import com.GabrielTiziano.NexWallet.dto.AssetDTO;
import com.GabrielTiziano.NexWallet.dto.UserRequestDTO;
import com.GabrielTiziano.NexWallet.dto.UserResponseDTO;
import com.GabrielTiziano.NexWallet.mapper.AssetMapper;
import com.GabrielTiziano.NexWallet.mapper.UserMapper;
import com.GabrielTiziano.NexWallet.model.AssetModel;
import com.GabrielTiziano.NexWallet.model.UserModel;
import com.GabrielTiziano.NexWallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO){
         return UserMapper
                 .toUserResponseDTO(userRepository.save(UserMapper.toModel(userRequestDTO)));
    }

    public UserResponseDTO addAsset(String id, AssetDTO assetDTO){
      UserModel user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Usuário não encontrado na NexWallet!"));

      AssetModel newAsset = AssetMapper.toModel(assetDTO);

      user.getAssets().add(newAsset);

      UserModel userUpdated = userRepository.save(user);

      return UserMapper.toUserResponseDTO(userUpdated);
    }
}
