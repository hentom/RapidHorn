package work.hennig.rapid_horn.rapid.expression;

public abstract class UnaryExpression extends Expression {

    protected Expression subexpression;

    protected UnaryExpression(Expression subexpression) {
        this.subexpression = subexpression;
    }

    public Expression getSubexpression() {
        return subexpression;
    }
}
