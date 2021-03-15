import java.util.*;
import java.util.stream.Collectors;

public class Dijkastra {

    private class Node {
        long count;
        boolean finished;
        String cca3;
        Node parent;

        Node(long count, boolean finished, String cca3, Node parent) {
            this.count = count;
            this.finished = finished;
            this.cca3 = cca3;
            this.parent = parent;
        }
    }

    private HashMap<String, Node> sumPopulation;
    private HashMap<String, Country> countries;


    public Dijkastra(String cca3, HashMap<String, Country> countries) {
        this.countries = countries;

        sumPopulation = new HashMap<>();
        sumPopulation.put(cca3, new Node(countries.get(cca3).getPopulation(), false, cca3, null));
    }

    public Queue<Country> getLessPopulation(String cca3) {
        Node node = null;
        while (!(sumPopulation.containsKey(cca3) && sumPopulation.get(cca3).finished)) {
            Country country = countries.get(sumPopulation.entrySet().stream().filter((i) -> !i.getValue().finished).min(Comparator.comparingLong(a -> a.getValue().count)).orElse(null).getKey());
            node = sumPopulation.get(country.getCca3());
            for (String border : country.getBorders().stream().filter(i -> !(sumPopulation.containsKey(i) && sumPopulation.get(i).finished)).collect(Collectors.toList())) {
                if (sumPopulation.putIfAbsent(border, new Node(node.count + countries.get(border).getPopulation(), false, border, node)) != null) {
                    if (node.count + countries.get(border).getPopulation() < sumPopulation.get(border).count) {
                        sumPopulation.get(border).count = node.count + countries.get(border).getPopulation();
                        sumPopulation.get(border).parent = node;
                    }
                }
            }
            sumPopulation.get(country.getCca3()).finished = true;
        }

        Queue<Country> queue = new LinkedList<>();
        while (node != null) {
            queue.add(countries.get(node.cca3));
            node = node.parent;
        }

        return queue;
    }
}
