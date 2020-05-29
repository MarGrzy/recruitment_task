package pl.mg.currConv.model;

public class UserInputDto {
    private final double amount;

    private final String currencyCode;

    public UserInputDto(final double amount, final String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public double getAmount() { return amount; }

    public String getCurrencyCode() { return currencyCode; }
}
