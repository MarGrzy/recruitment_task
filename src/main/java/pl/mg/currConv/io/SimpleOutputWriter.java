package pl.mg.currConv.io;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SimpleOutputWriter implements OutputWriter {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
    public void writeNoSuchCurrency() { System.out.println("Sorry, there is no such currency in database"); }
    public void writeWrongInputData() { System.out.println("Wrong input data"); }
    public void writeCurrencyConversionResult(String currCode, BigDecimal result) {
        System.out.println("Amount in " + currCode + ": " + result);
    }
}
