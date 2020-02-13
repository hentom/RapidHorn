package work.hennig.rapid_horn.rapid;

public abstract class Statement {

    public abstract void accept(RapidVisitor visitor);
}
