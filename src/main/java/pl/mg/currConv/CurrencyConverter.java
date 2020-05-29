package pl.mg.currConv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mg.currConv.api.DataManager;
import pl.mg.currConv.calculation.CalculationProcessor;
import pl.mg.currConv.interactionWithUser.InputReader;
import pl.mg.currConv.interactionWithUser.OutputWriter;
import pl.mg.currConv.model.UserInputDto;

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

        final double inputAmount = userInput.getAmount();
        final String currCode = userInput.getCurrencyCode();
        final double currRate = Double.parseDouble(dataManager.getCurrencyData().get(userInput.getCurrencyCode()));

        if (dataManager.getCurrencyData().containsKey(userInput.getCurrencyCode())) {
            final double result = (calculationProcessor.calculate(inputAmount, currCode, currRate));
            final double roundedResult = Math.round(result * 100d) / 100d;
            outputWriter.write("Amount in " + userInput.getCurrencyCode() + ": " + roundedResult);
        } else if (userInput.getCurrencyCode().matches(currencyPattern)) {
            outputWriter.write("Sorry, there is no such currency in database");
        } else {
            outputWriter.write("Wrong input data");
        }
    }
}
