package work.hennig.rapid_horn.ast.arithmetic_expression;

public class SubtractionExpression extends BinaryExpression {

    public SubtractionExpression(ArithmeticExpression left, ArithmeticExpression right) {
        super(left, right);
    }

    @Override
    public void accept(ArithmeticExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
