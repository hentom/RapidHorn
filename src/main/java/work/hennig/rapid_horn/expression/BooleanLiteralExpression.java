package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

public class BooleanLiteralExpression extends Expression {

    private boolean value;

    public BooleanLiteralExpression(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
