package com.GabrielTiziano.NexWallet.config;

import lombok.Builder;

@Builder
public record JWTUserData(String id, String name, String email) {
}
