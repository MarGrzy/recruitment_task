package pl.mg.currConv.api;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileParser {

    public Map<String, String> parseXMLElementsIntoJavaObjects(Document document) {

        final NodeList cubeList = document.getElementsByTagName("Cube");
        final Map<String, String> currMap = new HashMap<String, String>();
        for (int i = 0; i < cubeList.getLength(); i++) {
            final Node node = cubeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element currElement = (Element) node;

                String key = currElement.getAttribute("currency");
                String value = currElement.getAttribute("rate");
                currMap.put(key, value);

                if (currMap.get(key).isEmpty()) {
                    currMap.remove(key);
                }
            }
        }
        return currMap;
    }
}

