import java.util.HashSet;

public class Country {

  private String name;
  private String cca3;
  private HashSet<String> borders;

  public Country(String name, String cca3) {
    this.name = name;
    this.cca3 = cca3;
    this.borders = new HashSet<>();
  }

  public HashSet<String> getBorders() {
    return borders;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCca3() {
    return cca3;
  }

  public void setCca3(String cca3) {
    this.cca3 = cca3;
  }

  public boolean addBorder(String border) {
    return this.borders.add(border);
  }
}
