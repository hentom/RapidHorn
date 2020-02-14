package work.hennig.rapid_horn.cfg;

import work.hennig.rapid_horn.expression.Expression;

public class Assignment extends Transition {

    private Variable variable;
    private Expression expression;

    public Assignment(Location source, Location target, Variable variable, Expression expression) {
        super(source, target);
        this.variable = variable;
        this.expression = expression;
    }

    public Variable getVariable() {
        return variable;
    }

    public Expression getExpression() {
        return expression;
    }
}
