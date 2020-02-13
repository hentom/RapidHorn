package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

public class GreaterThanExpression extends BinaryExpression {

    public GreaterThanExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
