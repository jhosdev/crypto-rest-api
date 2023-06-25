package com.pokeswap.api.enums;

public enum Op_Type {

    CRYPTO_PURCHASE("Crypto Purchase"),
    WITHDRAW("Withdraw"),
    DEPOSIT("Deposit"),
    EXCHANGE("Exchange");

    private final String value;

    Op_Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
