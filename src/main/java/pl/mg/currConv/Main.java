package pl.mg.currConv;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-configuration.xml");

        final CurrencyConverter currencyConverter = context.getBean(CurrencyConverter.class);
        currencyConverter.start();
    }
}
