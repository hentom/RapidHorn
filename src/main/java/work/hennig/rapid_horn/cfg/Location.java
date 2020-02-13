package work.hennig.rapid_horn.cfg;

import java.util.LinkedList;
import java.util.List;

public class Location {

    private List<Variable> liveVariables;
    private List<Transition> incomingTransitions;
    private List<Transition> outgoingTransitions;

    public Location(List<Variable> liveVariables) {
        this.liveVariables = liveVariables;
        this.incomingTransitions = new LinkedList<>();
        this.outgoingTransitions = new LinkedList<>();
    }

    public List<Variable> getLiveVariables() {
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
