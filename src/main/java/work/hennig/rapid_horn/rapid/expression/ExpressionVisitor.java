package work.hennig.rapid_horn.rapid.expression;

public abstract class ExpressionVisitor {

    public abstract void visit(AdditionExpression expression);
    public abstract void visit(IntegerLiteralExpression expression);
    public abstract void visit(ModuloExpression expression);
    public abstract void visit(MultiplicationExpression expression);
    public abstract void visit(NegationExpression expression);
    public abstract void visit(SubtractionExpression expression);
    public abstract void visit(VariableExpression expression);

    public abstract void visit(AndExpression expression);
    public abstract void visit(BooleanLiteralExpression expression);
    public abstract void visit(EqualExpression expression);
    public abstract void visit(GreaterEqualExpression expression);
    public abstract void visit(GreaterThanExpression expression);
    public abstract void visit(LessEqualExpression expression);
    public abstract void visit(LessThanExpression expression);
    public abstract void visit(NotEqualExpression expression);
    public abstract void visit(NotExpression expression);
    public abstract void visit(OrExpression expression);
}
