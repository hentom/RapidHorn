package work.hennig.rapid_horn.ast.arithmetic_expression;

public class LiteralExpression extends ArithmeticExpression {

    private int value;

    public LiteralExpression(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(ArithmeticExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
