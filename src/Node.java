package src;

import java.util.LinkedList;

public class Node {
    private Country country;
    private Node parent;

    public Node(Country country, Node parent) {
        this.country = country;
        this.parent = parent;
    }

    public Country getCountry() {
        return country;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public LinkedList<Country> asLinkedList() {
        LinkedList<Country> countries = new LinkedList<>();
        Node current = this;
        while (current != null) {
            countries.push(current.getCountry());
            current = current.parent;
        }
        return countries;
    }
}
