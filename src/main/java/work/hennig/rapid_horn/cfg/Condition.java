package work.hennig.rapid_horn.cfg;

import work.hennig.rapid_horn.expression.Expression;

public class Condition extends Transition {

    private Expression condition;

    public Condition(Location source, Location target, Expression condition) {
        super(source, target);
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public void accept(CFGVisitor visitor) {
        visitor.visit(this);
    }
}
