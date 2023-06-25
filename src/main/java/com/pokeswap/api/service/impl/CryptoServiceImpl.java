package com.pokeswap.api.service.impl;

import com.pokeswap.api.model.Crypto;
import com.pokeswap.api.repository.CryptoRepository;
import com.pokeswap.api.service.CryptoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoServiceImpl implements CryptoService {

    private final CryptoRepository cryptoRepository;

    @Autowired
    public CryptoServiceImpl(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public Crypto createCrypto(Crypto crypto) {
        return cryptoRepository.save(crypto);
    }

    @Override
    public Crypto getCryptoById(Long id) {
        return cryptoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Crypto> getAllCryptos() {
        return cryptoRepository.findAll();
    }

    @Override
    public Crypto updateCrypto(Long id, Crypto crypto) {
        Crypto cryptoToUpdate = cryptoRepository.findById(id).orElse(null);
        if (cryptoToUpdate != null) {
            BeanUtils.copyProperties(crypto, cryptoToUpdate, "id");
            return cryptoRepository.save(cryptoToUpdate);
        }
        return null;
    }

    @Override
    public Crypto deleteCrypto(Long id) {
        cryptoRepository.updateCryptoStatus(id, false);
        return cryptoRepository.findById(id).orElse(null);
    }
}
