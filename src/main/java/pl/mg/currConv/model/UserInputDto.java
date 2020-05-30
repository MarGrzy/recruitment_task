package pl.mg.currConv.model;

import java.math.BigDecimal;

public class UserInputDto {
    private final BigDecimal amount;

    private final String currencyCode;

    public UserInputDto(final BigDecimal amount, final String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() { return amount; }

    public String getCurrencyCode() { return currencyCode; }
}
