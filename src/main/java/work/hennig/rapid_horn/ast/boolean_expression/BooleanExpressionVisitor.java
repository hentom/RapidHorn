package work.hennig.rapid_horn.ast.boolean_expression;

public abstract class BooleanExpressionVisitor {

    public abstract void visit(AndExpression expression);
    public abstract void visit(EqualExpression expression);
    public abstract void visit(GreaterEqualExpression expression);
    public abstract void visit(GreaterThanExpression expression);
    public abstract void visit(LessEqualExpression expression);
    public abstract void visit(LessThanExpression expression);
    public abstract void visit(NotEqualExpression expression);
    public abstract void visit(NotExpression expression);
    public abstract void visit(OrExpression expression);
}
