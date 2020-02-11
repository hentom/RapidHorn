package work.hennig.rapid_horn.ast.arithmetic_expression;

public class NegationExpression extends UnaryExpression {

    public NegationExpression(ArithmeticExpression subexpression) {
        super(subexpression);
    }

    @Override
    public void accept(ArithmeticExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
