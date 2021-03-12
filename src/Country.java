import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Country {

  private String name;
  private String cca3;
  private List<String> borders;
  private long population;

  public Country(String name, String cca3, long population) {
    this.name = name;
    this.cca3 = cca3;
    this.population = population;
    this.borders = new ArrayList<>();
  }

  public List<String> getBorders() {
    return borders;
  }

  public String getName() {
    return name;
  }

  public String getCca3() {
    return cca3;
  }

  public long getPopulation() {
    return population;
  }

  public boolean addBorder(String border) {
    return this.borders.add(border);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Country country = (Country) o;
    return Objects.equals(cca3, country.cca3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cca3);
  }
}
