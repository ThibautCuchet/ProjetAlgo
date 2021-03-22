package src;

import java.util.*;
import java.util.stream.Collectors;

public class Dijkastra {

    private class DijkastraNode extends Node{
        private long count;
        private boolean finished;

        DijkastraNode(long count, boolean finished,Country country, Node parent) {
            super(country, parent);
            this.count = count;
            this.finished = finished;
        }

        public long getCount() {
            return count;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public void setCount(long count) {
            this.count = count;
        }
    }

    private HashMap<String, DijkastraNode> sumPopulation;
    private HashMap<String, Country> countries;


    public Dijkastra(String cca3, HashMap<String, Country> countries) {
        this.countries = countries;

        sumPopulation = new HashMap<>();
        sumPopulation.put(cca3, new DijkastraNode(countries.get(cca3).getPopulation(), false, countries.get(cca3), null));
    }

    public Queue<Country> getLessPopulation(String cca3) {
        DijkastraNode node = null;

        try {
            while (!(sumPopulation.containsKey(cca3) && sumPopulation.get(cca3).isFinished())) {
                Country country = countries.get(sumPopulation.entrySet().stream().filter((i) -> !i.getValue().finished).min(Comparator.comparingLong(a -> a.getValue().getCount())).orElse(null).getKey());
                node = sumPopulation.get(country.getCca3());
                for (String border : country.getBorders().stream().filter(i -> !(sumPopulation.containsKey(i) && sumPopulation.get(i).isFinished())).collect(Collectors.toList())) {
                    if (sumPopulation.putIfAbsent(border, new DijkastraNode(node.getCount() + countries.get(border).getPopulation(), false, countries.get(border), node)) != null) {
                        if (node.getCount() + countries.get(border).getPopulation() < sumPopulation.get(border).getCount()) {
                            sumPopulation.get(border).setCount(node.getCount() + countries.get(border).getPopulation());
                            sumPopulation.get(border).setParent(node);
                        }
                    }
                }
                sumPopulation.get(country.getCca3()).finished = true;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("It's impossible to join these countries");
        }
        return node.asLinkedList();
    }
}
