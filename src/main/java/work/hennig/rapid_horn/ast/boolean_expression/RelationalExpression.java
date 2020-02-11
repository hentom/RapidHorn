package work.hennig.rapid_horn.ast.boolean_expression;

import work.hennig.rapid_horn.ast.arithmetic_expression.ArithmeticExpression;

public abstract class RelationalExpression extends BooleanExpression {

    protected ArithmeticExpression left;
    protected ArithmeticExpression right;

    protected RelationalExpression(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    public ArithmeticExpression getLeft() {
        return left;
    }

    public ArithmeticExpression getRight() {
        return right;
    }
}
