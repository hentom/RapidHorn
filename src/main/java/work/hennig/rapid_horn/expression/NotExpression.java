package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

public class NotExpression extends UnaryExpression {

    public NotExpression(Expression subexpression) {
        super(subexpression);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
