package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

public class IntegerLiteralExpression extends Expression {

    private int value;

    public IntegerLiteralExpression(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
