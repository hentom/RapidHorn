package work.hennig.rapid_horn.cfg;

import java.util.LinkedList;
import java.util.List;

public class Location {

    private List<Declaration> liveVariables;
    private List<Transition> incomingTransitions;
    private List<Transition> outgoingTransitions;

    public Location(List<Declaration> liveVariables) {
        this.liveVariables = liveVariables;
        this.incomingTransitions = new LinkedList<>();
        this.outgoingTransitions = new LinkedList<>();
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
}
