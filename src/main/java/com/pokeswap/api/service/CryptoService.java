package com.pokeswap.api.service;

import com.pokeswap.api.model.Crypto;

import java.util.List;

public interface CryptoService {
    Crypto createCrypto(Crypto crypto);

    Crypto getCryptoById(Long id);

    List<Crypto> getAllCryptos();

    Crypto updateCrypto(Long id, Crypto crypto);

    Crypto deleteCrypto(Long id);
}
