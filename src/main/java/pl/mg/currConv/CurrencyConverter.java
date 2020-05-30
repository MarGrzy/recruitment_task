package pl.mg.currConv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mg.currConv.api.DataManager;
import pl.mg.currConv.calculation.CalculationProcessor;
import pl.mg.currConv.io.InputReader;
import pl.mg.currConv.io.OutputWriter;
import pl.mg.currConv.model.UserInputDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CurrencyConverter {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final CalculationProcessor calculationProcessor;
    private final DataManager dataManager;

    @Autowired
    public CurrencyConverter(final InputReader inputReader, final OutputWriter outputWriter, CalculationProcessor calculationProcessor, DataManager dataManager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.calculationProcessor = calculationProcessor;
        this.dataManager = dataManager;
    }

    public void start() {
        final UserInputDto userInput = inputReader.getUserInput();

        String currencyPattern = "[a-zA-Z]{3}";

        final BigDecimal inputAmount = userInput.getAmount();
        final String currCode = userInput.getCurrencyCode();
        final BigDecimal currRate = dataManager.getCurrencyData().get(userInput.getCurrencyCode());

        if (dataManager.getCurrencyData().containsKey(userInput.getCurrencyCode())) {
            final BigDecimal result = (calculationProcessor.calculate(inputAmount, currCode, currRate));
            final BigDecimal roundedResult = result.setScale(2, RoundingMode.CEILING);
            outputWriter.writeCurrencyConversionResult(userInput.getCurrencyCode(), roundedResult);
        } else if (userInput.getCurrencyCode().matches(currencyPattern)) {
            outputWriter.writeNoSuchCurrency();
        } else {
            outputWriter.writeWrongInputData();
        }
    }
}
