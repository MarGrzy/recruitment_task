package pl.mg.currConv.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataManager {

    @Autowired
    public FileReader fileReader = new FileReader();
    public FileParser fileParser = new FileParser();

    public Map<String, BigDecimal> getCurrencyData() {
        return new HashMap<String, BigDecimal>(fileParser.parseXMLElementsIntoJavaObjects(fileReader.createDocument()));
    }
}
