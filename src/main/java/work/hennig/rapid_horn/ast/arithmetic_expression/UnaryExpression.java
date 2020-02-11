package work.hennig.rapid_horn.ast.arithmetic_expression;

public abstract class UnaryExpression extends ArithmeticExpression {

    protected ArithmeticExpression subexpression;

    protected UnaryExpression(ArithmeticExpression subexpression) {
        this.subexpression = subexpression;
    }

    public ArithmeticExpression getSubexpression() {
        return subexpression;
    }
}
