package net.melonbun.melonbun.common.model;

public class Price {

    private Double value;
    private String currency;

    public Price(Double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}
