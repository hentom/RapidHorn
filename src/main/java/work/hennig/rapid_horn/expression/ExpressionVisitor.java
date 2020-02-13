package work.hennig.rapid_horn.expression;

public interface ExpressionVisitor {

    void visit(AdditionExpression expression);
    void visit(IntegerLiteralExpression expression);
    void visit(ModuloExpression expression);
    void visit(MultiplicationExpression expression);
    void visit(NegationExpression expression);
    void visit(SubtractionExpression expression);
    void visit(VariableExpression expression);

    void visit(AndExpression expression);
    void visit(BooleanLiteralExpression expression);
    void visit(EqualExpression expression);
    void visit(GreaterEqualExpression expression);
    void visit(GreaterThanExpression expression);
    void visit(LessEqualExpression expression);
    void visit(LessThanExpression expression);
    void visit(NotEqualExpression expression);
    void visit(NotExpression expression);
    void visit(OrExpression expression);
}
