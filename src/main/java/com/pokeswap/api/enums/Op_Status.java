package com.pokeswap.api.enums;

public enum Op_Status {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private String value;

    Op_Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
