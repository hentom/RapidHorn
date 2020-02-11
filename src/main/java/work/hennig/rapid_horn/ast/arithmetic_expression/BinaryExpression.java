package work.hennig.rapid_horn.ast.arithmetic_expression;

public abstract class BinaryExpression extends ArithmeticExpression {

    protected ArithmeticExpression left;
    protected ArithmeticExpression right;

    protected BinaryExpression(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    public ArithmeticExpression getLeft() {
        return left;
    }

    public ArithmeticExpression getRight() {
        return right;
    }
}
