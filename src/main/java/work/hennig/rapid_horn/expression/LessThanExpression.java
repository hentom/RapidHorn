package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

public class LessThanExpression extends BinaryExpression {

    public LessThanExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
