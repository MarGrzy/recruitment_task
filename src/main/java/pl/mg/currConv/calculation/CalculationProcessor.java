package pl.mg.currConv.calculation;

import org.springframework.stereotype.Component;

@Component
public class CalculationProcessor {

    public Double calculate(final double amount, final String currency, final double currRate) {
        return amount * currRate;
    }
}
