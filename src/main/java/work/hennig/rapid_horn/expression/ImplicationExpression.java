package work.hennig.rapid_horn.expression;

public class ImplicationExpression extends BinaryExpression {

    public ImplicationExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
