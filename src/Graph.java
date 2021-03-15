import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Graph {

  private HashMap<String, Country> countries;

  public Graph() {
    countries = new HashMap<>();
  }

  public Country addCountry(String cca3, Country country) {
    return countries.put(cca3, country);
  }

  public HashMap<String, Country> getCountries() {
    return countries;
  }

  public void calculerItineraireMinimisantNombreDeFrontieres(String origin, String dest, String output) {
    BFS bfs = new BFS(origin, countries);
    LinkedList<Country> countryQueue = (LinkedList) bfs.getShortestWay(dest);
    writeXML(countryQueue, origin, dest, output);
  }

  public void calculerItineraireMinimisantPopulationTotale(String origin, String dest, String out) {
    Dijkastra dijkastra = new Dijkastra(origin, countries);
    LinkedList<Country> countryQueue = (LinkedList) dijkastra.getLessPopulation(dest);
    writeXML(countryQueue, origin, dest, out);

  }

  private void writeXML(LinkedList<Country> countryQueue, String origin, String dest, String output) {
    long sumPopulation = countryQueue.stream().map(Country::getPopulation).reduce(Long::sum).orElse(
            (long) 0);

    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();
      Element rootElement = document.createElement("itineraire");
      rootElement.setAttribute("arrivee", this.countries.get(dest).getName());
      rootElement.setAttribute("depart", this.countries.get(origin).getName());
      rootElement.setAttribute("nbPays", String.valueOf(countryQueue.size()));
      rootElement.setAttribute("sommePopulation", String.valueOf(sumPopulation));
      document.appendChild(rootElement);

      Element childElement;
      Country country;
      while (countryQueue.size() > 0) {
        country = countryQueue.removeLast();
        childElement = document.createElement("pays");
        childElement.setAttribute("cca3", country.getCca3());
        childElement.setAttribute("nom", country.getName());
        childElement.setAttribute("population", String.valueOf(country.getPopulation()));
        rootElement.appendChild(childElement);
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(new File(output));
      transformer.transform(source, result);
    } catch (ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }
  }
}
