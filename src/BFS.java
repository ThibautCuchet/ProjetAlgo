import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

  private Queue<BFSNode> queue;
  private Set<Country> added;
  private BFSNode root;
  private BFSNode destNode;
  private HashMap<String, Country> countries;

  public BFS(String cca3, HashMap<String, Country> countries) {
    queue = new LinkedList<>();
    added = new HashSet<>();

    Country startCountry = countries.get(cca3);
    root = new BFSNode(startCountry, null);
    queue.add(root);
    added.add(startCountry);
    this.countries = countries;
  }

  public Queue<Country> getShortestWay(String cca3) {
    boolean isArrived = false;
    try {
      BFSNode currentNode = queue.poll();
      while (!isArrived) {
        for (String border : currentNode.getCountry().getBorders()) {
          if (!this.added.contains(this.countries.get(border))) {
            BFSNode child = new BFSNode(this.countries.get(border), currentNode);
            currentNode.addChildren(child);
            queue.add(child);
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
    Queue<Country> countryQueue = new LinkedList<>();
    BFSNode currentNode = destNode;

    while (currentNode.getParent() != null) {
      countryQueue.add(currentNode.getCountry());
      currentNode = currentNode.getParent();
    }
    countryQueue.add(currentNode.getCountry());
    return countryQueue;
  }
}
