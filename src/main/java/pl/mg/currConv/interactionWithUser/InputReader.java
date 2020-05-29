package pl.mg.currConv.interactionWithUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mg.currConv.model.UserInputDto;

import java.util.Locale;
import java.util.Scanner;

@Component
public class InputReader {

    private final OutputWriter outputWriter;

    @Autowired
    public InputReader(final OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    public UserInputDto getUserInput() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        outputWriter.write("Please enter the amount in EUR");
        final double amount = scanner.nextDouble();
        outputWriter.write("Please enter the currency code");
        outputWriter.write("(for example: USD, PLN, CZK)");
        final String currencyCode = scanner.next().toUpperCase();
        return new UserInputDto(amount, currencyCode);
    }
}
