package work.hennig.rapid_horn.cfg;

public class Skip extends Transition {

    public Skip(Location source, Location target) {
        super(source, target);
    }

    @Override
    public void accept(CFGVisitor visitor) {
        visitor.visit(this);
    }
}
