package work.hennig.rapid_horn.rapid;

public class SkipStatement extends Statement {

    @Override
    public void accept(RapidVisitor visitor) {
        visitor.visit(this);
    }
}
