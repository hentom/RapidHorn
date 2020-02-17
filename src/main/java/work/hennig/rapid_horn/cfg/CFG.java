package work.hennig.rapid_horn.cfg;

import work.hennig.rapid_horn.rapid.Declaration;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CFG {

    private List<Location> locations;
    private List<Transition> transitions;
    private List<Declaration> declarations;
    private Optional<Location> startLocation;
    private List<Location> errorLocations;

    public CFG() {
        this.locations = new LinkedList<>();
        this.transitions = new LinkedList<>();
        this.declarations = new LinkedList<>();
        this.startLocation = Optional.empty();
        this.errorLocations = new LinkedList<>();
    }

    public CFG(List<Location> locations, List<Transition> transitions, List<Declaration> declarations,
               Location start, Location error) {
        this.locations = locations;
        this.transitions = transitions;
        this.declarations = declarations;
        this.startLocation = Optional.of(start);
        this.errorLocations = new LinkedList<>();
        this.errorLocations.add(error);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public boolean hasStart() {
        return startLocation.isPresent();
    }

    public Location getStartLocation() {
        return startLocation.get();
    }

    public List<Location> getErrorLocations() {
        return errorLocations;
    }

    public CFG add(CFG other) {
        this.locations.addAll(other.locations);
        this.transitions.addAll(other.transitions);
        this.declarations.addAll(other.declarations);
        this.errorLocations.addAll(other.errorLocations);

        return this;
    }

    public CFG add(CFG other, Location start) {
        this.locations.addAll(other.locations);
        this.transitions.addAll(other.transitions);
        this.declarations.addAll(other.declarations);
        this.startLocation = Optional.of(start);
        this.errorLocations.addAll(other.errorLocations);

        return this;
    }

    public void accept(CFGVisitor visitor) {
        visitor.visit(this);
    }
}
