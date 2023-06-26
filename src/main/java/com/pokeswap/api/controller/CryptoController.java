package com.pokeswap.api.controller;

import com.pokeswap.api.model.Crypto;
import com.pokeswap.api.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/pokeswap/v1")
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Transactional
    @PostMapping("/cryptos")
    public ResponseEntity<Crypto> createCrypto(@RequestBody Crypto crypto) {
        return new ResponseEntity<>(cryptoService.createCrypto(crypto), HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/cryptos")
    public ResponseEntity<Iterable<Crypto>> getAllCryptos() {
        return new ResponseEntity<>(cryptoService.getAllCryptos(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> getCryptoById(@PathVariable Long id) {
        return new ResponseEntity<>(cryptoService.getCryptoById(id), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> updateCrypto(@PathVariable Long id, @RequestBody Crypto crypto) {
        return new ResponseEntity<>(cryptoService.updateCrypto(id, crypto), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> deleteCrypto(@PathVariable Long id) {
        return new ResponseEntity<>(cryptoService.deleteCrypto(id), HttpStatus.OK);
    }
}
