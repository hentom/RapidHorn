package work.hennig.rapid_horn.cfg;

import work.hennig.rapid_horn.expression.Expression;

public class Assignment extends Transition {

    private String identifier;
    private Expression expression;

    public Assignment(Location source, Location target, String identifier, Expression expression) {
        super(source, target);
        this.identifier = identifier;
        this.expression = expression;
    }
}
