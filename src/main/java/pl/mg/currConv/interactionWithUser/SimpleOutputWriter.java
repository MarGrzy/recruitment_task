package pl.mg.currConv.interactionWithUser;

import org.springframework.stereotype.Component;

@Component
public class SimpleOutputWriter implements OutputWriter {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
