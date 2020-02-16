package work.hennig.rapid_horn.cfg;

public abstract class Transition {

    protected Location source;
    protected Location target;

    protected Transition(Location source, Location target) {
        this.source = source;
        this.target = target;
    }

    public Location getSource() {
        return source;
    }

    public Location getTarget() {
        return target;
    }

    public abstract void accept(CFGVisitor visitor);
}
