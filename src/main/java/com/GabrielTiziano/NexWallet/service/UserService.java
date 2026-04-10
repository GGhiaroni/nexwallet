package com.GabrielTiziano.NexWallet.service;

import com.GabrielTiziano.NexWallet.client.CoinGeckoClient;
import com.GabrielTiziano.NexWallet.dto.*;
import com.GabrielTiziano.NexWallet.mapper.AssetMapper;
import com.GabrielTiziano.NexWallet.mapper.UserMapper;
import com.GabrielTiziano.NexWallet.model.AssetModel;
import com.GabrielTiziano.NexWallet.model.UserModel;
import com.GabrielTiziano.NexWallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CoinGeckoClient coinGeckoClient;

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

    public PortfolioResponseDTO getUserPortfolio(String userId){
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado na NexWallet!"));

        if (user.getAssets().isEmpty()) {
            return new PortfolioResponseDTO(0.0, new ArrayList<>());
        }

        String cryptoIds = user.getAssets().stream()
                .map(AssetModel::getCryptoId)
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        Map<String, Map<String, Double>> apiResponse = coinGeckoClient.getPrice(cryptoIds, "usd");

        Double totalBalance = 0.0;
        List<AssetPortfolioDTO> portfolioAssets = new ArrayList<>();

        for(AssetModel asset : user.getAssets()){

            String coinName = asset.getCryptoId();

            Double currentPrice = apiResponse.get(coinName).get("usd");

            Double totalValue = asset.getQuantity() * currentPrice;

            totalBalance += totalValue;

            AssetPortfolioDTO assetPortfolioDTO = AssetPortfolioDTO
                    .builder()
                    .cryptoId(coinName)
                    .quantity(asset.getQuantity())
                    .currentPrice(currentPrice)
                    .totalValue(totalValue)
                    .build();

            portfolioAssets.add(assetPortfolioDTO);
        }

        return PortfolioResponseDTO.builder()
                .totalBalance(totalBalance)
                .assets(portfolioAssets)
                .build();
    }
}
