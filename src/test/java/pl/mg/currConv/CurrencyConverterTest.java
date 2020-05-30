package pl.mg.currConv;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import pl.mg.currConv.calculation.CalculationProcessor;
import pl.mg.currConv.io.InputReader;
import pl.mg.currConv.io.OutputWriter;
import pl.mg.currConv.model.UserInputDto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class CurrencyConverterTest {
    private final BigDecimal inputAmount = BigDecimal.valueOf(25.5);
    private final String inputCurrencyCode = "CzK";
    private final BigDecimal receivedCurrencyRate = BigDecimal.valueOf(2);
    private final UserInputDto inputDto = new UserInputDto(inputAmount, inputCurrencyCode);

    @Mock
    private InputReader inputReader;
    @Mock
    private OutputWriter outputWriter;
    @Mock
    private CalculationProcessor calculationProcessor;
    @Captor
    private ArgumentCaptor<String> outputCaptor;

    @InjectMocks
    private CurrencyConverter currencyConverter;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_wrong_input_data() {
        when(inputReader.getUserInput()).thenReturn(inputDto);
        when(calculationProcessor.calculate(eq(inputAmount), eq("dollars"), eq(receivedCurrencyRate))).thenReturn(inputAmount.multiply(receivedCurrencyRate));

        currencyConverter.start();

        verify(outputWriter).writeWrongInputData();
    }

    @Test
    public void should_return_no_such_currency_in_database() {
        when(inputReader.getUserInput()).thenReturn(inputDto);
        when(calculationProcessor.calculate(eq(inputAmount), eq("PLK"), eq(receivedCurrencyRate))).thenReturn(inputAmount.multiply(receivedCurrencyRate));

        currencyConverter.start();

        verify(outputWriter).writeNoSuchCurrency();
    }

    @Test
    public void should_return_calculation_result() {
        when(inputReader.getUserInput()).thenReturn(inputDto);
        when(calculationProcessor.calculate(eq(inputAmount), eq(inputCurrencyCode), eq(receivedCurrencyRate))).thenReturn(BigDecimal.valueOf(51));

        currencyConverter.start();

        verify(outputWriter, times(1)).write(outputCaptor.capture());
        final String outputString = outputCaptor.getValue();
        assertThat(outputString).contains("51");
    }
}
