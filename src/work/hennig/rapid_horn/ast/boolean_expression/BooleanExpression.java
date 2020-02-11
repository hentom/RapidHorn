package work.hennig.rapid_horn.ast.boolean_expression;

public abstract class BooleanExpression {

    public abstract void accept(BooleanExpressionVisitor visitor);
}
