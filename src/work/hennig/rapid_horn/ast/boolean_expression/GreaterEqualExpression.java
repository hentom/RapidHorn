package work.hennig.rapid_horn.ast.boolean_expression;

import work.hennig.rapid_horn.ast.arithmetic_expression.ArithmeticExpression;

public class GreaterEqualExpression extends RelationalExpression {

    public GreaterEqualExpression(ArithmeticExpression left, ArithmeticExpression right) {
        super(left, right);
    }

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
