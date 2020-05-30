package pl.mg.currConv.io;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

@RunWith(MockitoJUnitRunner.class)
public class InputReaderTest {

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Mock
    private PrintStream printStream;

    @Test
    public void should_GetUserInput() {
        System.setOut(printStream);

        System.out.println("abc");

        Mockito.verify(printStream).println(stringCaptor.capture());
        Assertions.assertThat("abc").isEqualTo(stringCaptor.getValue());
    }
}