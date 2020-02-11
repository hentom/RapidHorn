package work.hennig.rapid_horn.ast.arithmetic_expression;

public abstract class ArithmeticExpressionVisitor {

    public abstract void visit(AdditionExpression expression);
    public abstract void visit(LiteralExpression expression);
    public abstract void visit(ModuloExpression expression);
    public abstract void visit(MultiplicationExpression expression);
    public abstract void visit(NegationExpression expression);
    public abstract void visit(SubtractionExpression expression);
    public abstract void visit(VariableExpression expression);
}
