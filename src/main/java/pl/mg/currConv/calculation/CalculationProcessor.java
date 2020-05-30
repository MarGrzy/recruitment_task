package pl.mg.currConv.calculation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculationProcessor {

    public BigDecimal calculate(final BigDecimal amount, final String currency, final BigDecimal currRate) {
        return amount.multiply(currRate);
    }
}
