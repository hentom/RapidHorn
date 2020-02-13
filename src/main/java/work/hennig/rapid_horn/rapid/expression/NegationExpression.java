package work.hennig.rapid_horn.rapid.expression;

import work.hennig.rapid_horn.rapid.Type;

public class NegationExpression extends UnaryExpression {

    public NegationExpression(Expression subexpression) {
        super(subexpression);
    }

    @Override
    public Type returnType() {
        return Type.INTEGER;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
