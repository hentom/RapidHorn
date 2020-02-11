package work.hennig.rapid_horn.ast.arithmetic_expression;

public abstract class ArithmeticExpression {

    public abstract void accept(ArithmeticExpressionVisitor visitor);
}
