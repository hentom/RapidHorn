package work.hennig.rapid_horn.expression;

public abstract class BinaryExpression extends Expression {

    protected Expression left;
    protected Expression right;

    protected BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
