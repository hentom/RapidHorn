package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

public class NegationExpression extends UnaryExpression {

    public NegationExpression(Expression subexpression) {
        super(subexpression);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
