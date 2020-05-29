package pl.mg.currConv.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataManager {

    @Autowired
    public FileReader fileReader = new FileReader();
    public FileParser fileParser = new FileParser();

    public Map<String, String> getCurrencyData() {
        final Map<String, String> currMap = new HashMap<String, String>();
        try {
            currMap.putAll(fileParser.parseXMLElementsIntoJavaObjects(fileReader.createDocument()));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return currMap;
    }
}
