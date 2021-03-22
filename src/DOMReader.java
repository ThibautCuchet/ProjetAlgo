package src;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMReader {


  public static Graph getGraph(String input) {
    Graph graph = new Graph();

    try {
      File xmlFile = new File(input);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(xmlFile);

      NodeList countries = doc.getElementsByTagName("country");
      for(int i = 0; i < countries.getLength(); i++) {
        Element element = (Element) countries.item(i);
        Country country = new Country(element.getAttribute("name"), element.getAttribute("cca3"), Long.parseLong(element.getAttribute("population")));
        graph.addCountry(element.getAttribute("cca3"), country);
        NodeList borders = doc.getElementsByTagName("border");
        for(int j = 0; j < borders.getLength(); j++) {
          country.addBorder(borders.item(j).getTextContent());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return graph;
  }
}
