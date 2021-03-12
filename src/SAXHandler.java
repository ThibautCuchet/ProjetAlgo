import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

  private Country country;
  private boolean isBorder;
  private Graph graph;

  @Override
  public void startDocument() throws SAXException {
    graph = new Graph();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    if(qName.equalsIgnoreCase("country")) {
      country = new Country(attributes.getValue("name"), attributes.getValue("cca3"));
      graph.addCountry(country.getCca3(), country);
    }
    if(qName.equalsIgnoreCase("border")) {
      isBorder = true;
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if(isBorder) {
      country.addBorder(new String(ch, start, length));
      isBorder = false;
    }
  }

  public Graph getGraph() {
    return this.graph;
  }
}
