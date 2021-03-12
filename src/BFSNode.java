import java.util.ArrayList;
import java.util.List;

public class BFSNode {
  private Country country;
  private BFSNode parent;
  private List<BFSNode> children;

  public BFSNode(Country country, BFSNode parent) {
    this.country = country;
    this.parent = parent;
    this.children = new ArrayList<>();
  }

  public Country getCountry() {
    return country;
  }

  public BFSNode getParent() {
    return parent;
  }

  public List<BFSNode> getChildren() {
    return children;
  }

  public void addChildren(BFSNode child) {
    children.add(child);
  }
}
