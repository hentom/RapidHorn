package work.hennig.rapid_horn.cfg;

import work.hennig.rapid_horn.expression.Expression;

public class Array extends Variable {

    private Expression index;

    public Array(String identifier, Expression index) {
        super(identifier);
        this.index = index;
    }

    public Expression getIndex() {
        return index;
    }
}
