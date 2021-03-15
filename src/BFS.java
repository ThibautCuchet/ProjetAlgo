import java.util.*;

public class BFS {

  private Queue<Node> queue;
  private Set<Country> added;
  private Node root;
  private Node destNode;
  private HashMap<String, Country> countries;


  public BFS(String cca3, HashMap<String, Country> countries) {
    queue = new LinkedList<>();
    added = new HashSet<>();

    Country startCountry = countries.get(cca3);
    root = new Node(startCountry, null);
    queue.add(root);
    added.add(startCountry);
    this.countries = countries;
  }

  public Queue<Country> getShortestWay(String cca3) {
    boolean isArrived = false;
    try {
      Node currentNode = queue.poll();
      while (!isArrived) {
        for (String border : currentNode.getCountry().getBorders()) {
          if (!this.added.contains(this.countries.get(border))) {
            Node child = new Node(this.countries.get(border), currentNode);
            queue.add(child);
            added.add(child.getCountry());
            if (border.equalsIgnoreCase(cca3)) {
              isArrived = true;
              destNode = child;
            }
          }
        }
        currentNode = queue.poll();
      }

    } catch (NullPointerException e) {
      throw new IllegalArgumentException("It's impossible to join these countries");
    }

    return destNode.asLinkedList();
  }
}
