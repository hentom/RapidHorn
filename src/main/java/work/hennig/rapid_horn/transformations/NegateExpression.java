package work.hennig.rapid_horn.transformations;

import work.hennig.rapid_horn.expression.*;

public class NegateExpression implements ExpressionVisitor {

    private Expression current;

    private NegateExpression() {
        this.current = null;
    }

    public static Expression negate(Expression expression) {
        NegateExpression transformation = new NegateExpression();
        expression.accept(transformation);
        return transformation.current;
    }

    @Override
    public void visit(AdditionExpression expression) {
        throw new UnsupportedOperationException("cannot negate arithmetic expression");
    }

    @Override
    public void visit(IntegerLiteralExpression expression) {
        throw new UnsupportedOperationException("cannot negate arithmetic expression");
    }

    @Override
    public void visit(ModuloExpression expression) {
        throw new UnsupportedOperationException("cannot negate arithmetic expression");
    }

    @Override
    public void visit(MultiplicationExpression expression) {
        throw new UnsupportedOperationException("cannot negate arithmetic expression");
    }

    @Override
    public void visit(NegationExpression expression) {
        throw new UnsupportedOperationException("cannot negate arithmetic expression");
    }

    @Override
    public void visit(SubtractionExpression expression) {
        throw new UnsupportedOperationException("cannot negate arithmetic expression");
    }

    @Override
    public void visit(VariableExpression expression) {
        throw new UnsupportedOperationException("cannot negate arithmetic expression");
    }

    @Override
    public void visit(AndExpression expression) {
        expression.getLeft().accept(this);
        Expression leftNegated = current;

        expression.getRight().accept(this);

        current = new OrExpression(leftNegated, current);
    }

    @Override
    public void visit(BooleanLiteralExpression expression) {
        current = new BooleanLiteralExpression(!expression.getValue());
    }

    @Override
    public void visit(EqualExpression expression) {
        current = new NotEqualExpression(expression.getLeft(), expression.getRight());
    }

    @Override
    public void visit(GreaterEqualExpression expression) {
        current = new LessThanExpression(expression.getLeft(), expression.getRight());
    }

    @Override
    public void visit(GreaterThanExpression expression) {
        current = new LessEqualExpression(expression.getLeft(), expression.getRight());
    }

    @Override
    public void visit(ImplicationExpression expression) {
        expression.getRight().accept(this);
        current = new AndExpression(expression.getLeft(), current);
    }

    @Override
    public void visit(LessEqualExpression expression) {
        current = new GreaterThanExpression(expression.getLeft(), expression.getRight());
    }

    @Override
    public void visit(LessThanExpression expression) {
        current = new GreaterEqualExpression(expression.getLeft(), expression.getRight());
    }

    @Override
    public void visit(NotEqualExpression expression) {
        current = new EqualExpression(expression.getLeft(), expression.getRight());
    }

    @Override
    public void visit(NotExpression expression) {
        current = expression.getSubexpression();
    }

    @Override
    public void visit(OrExpression expression) {
        expression.getLeft().accept(this);
        Expression leftNegated = current;

        expression.getRight().accept(this);

        current = new AndExpression(leftNegated, current);
    }

    @Override
    public void visit(RelationExpression expression) {
        current = new NotExpression(expression);
    }
}
