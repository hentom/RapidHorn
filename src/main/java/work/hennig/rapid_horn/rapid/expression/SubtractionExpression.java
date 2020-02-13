package work.hennig.rapid_horn.rapid.expression;

import work.hennig.rapid_horn.rapid.Type;

public class SubtractionExpression extends BinaryExpression {

    public SubtractionExpression(Expression left, Expression right) {
        super(left, right);
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
