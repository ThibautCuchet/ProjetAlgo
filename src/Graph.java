import java.util.HashMap;

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
}
