package work.hennig.rapid_horn.ast.arithmetic_expression;

public class MultiplicationExpression extends BinaryExpression {

    public MultiplicationExpression(ArithmeticExpression left, ArithmeticExpression right) {
        super(left, right);
    }

    @Override
    public void accept(ArithmeticExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
