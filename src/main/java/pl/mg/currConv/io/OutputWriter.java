package pl.mg.currConv.io;

import java.math.BigDecimal;

public interface OutputWriter {
    // Classic writer
    public void write(String message);

    // Output writer
    public void writeNoSuchCurrency();
    public void writeWrongInputData();
    public void writeCurrencyConversionResult(final String currCode, final BigDecimal result);
}
