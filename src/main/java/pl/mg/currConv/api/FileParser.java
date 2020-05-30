package pl.mg.currConv.api;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileParser {

    public Map<String, BigDecimal> parseXMLElementsIntoJavaObjects(Document document) {

        final NodeList cubeList = document.getElementsByTagName("Cube");
        final Map<String, String> tempMap = new HashMap<String, String>();
        final Map<String, BigDecimal> currMap = new HashMap<String, BigDecimal>();
        for (int i = 0; i < cubeList.getLength(); i++) {
            final Node node = cubeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
               final Element currElement = (Element) node;

                final String key = currElement.getAttribute("currency");
                final String rate = currElement.getAttribute("rate");

                tempMap.put(key, rate);

                if (tempMap.get(key).isEmpty()) {
                    tempMap.remove(key);
                }
                for (String keys : tempMap.keySet()) {

                    final BigDecimal valueDecimal = new BigDecimal(tempMap.get(keys));
                    currMap.put(keys, valueDecimal);
                }
            }
        }
        return currMap;
    }
}

