package work.hennig.rapid_horn.cfg;

import work.hennig.rapid_horn.rapid.Declaration;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Location {

    private String identifier;
    private List<Declaration> liveVariables;
    private List<Transition> incomingTransitions;
    private List<Transition> outgoingTransitions;

    private static int counter = 0;

    private static int nextCounter() {
        return counter++;
    }

    public Location(List<Declaration> liveVariables) {
        this.identifier = "loc" + nextCounter();
        this.liveVariables = liveVariables;
        this.incomingTransitions = new LinkedList<>();
        this.outgoingTransitions = new LinkedList<>();
    }

    public Location(String identifier, List<Declaration> liveVariables) {
        this.identifier = identifier;
        this.liveVariables = liveVariables;
        this.incomingTransitions = new LinkedList<>();
        this.outgoingTransitions = new LinkedList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Declaration> getLiveVariables() {
        return liveVariables;
    }

    public List<Transition> getIncomingTransitions() {
        return incomingTransitions;
    }

    public List<Transition> getOutgoingTransitions() {
        return outgoingTransitions;
    }

    public boolean addIncomingTransition(Transition transition) {
        return incomingTransitions.add(transition);
    }

    public boolean addOutgoingTransition(Transition transition) {
        return outgoingTransitions.add(transition);
    }

    public void accept(CFGVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return identifier.equals(location.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
