package work.hennig.rapid_horn.transformations;

import work.hennig.rapid_horn.expression.*;

public class Expression2SMTLIB implements ExpressionVisitor {

    private StringBuilder builder;

    private Expression2SMTLIB() {
        builder = new StringBuilder();
    }

    public static String transform(Expression expression) {
        Expression2SMTLIB transformer = new Expression2SMTLIB();
        expression.accept(transformer);
        return transformer.builder.toString();
    }

    @Override
    public void visit(AdditionExpression expression) {
        builder.append("( + ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(IntegerLiteralExpression expression) {
        builder.append(expression.getValue());
    }

    @Override
    public void visit(ModuloExpression expression) {
        builder.append("( mod ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(MultiplicationExpression expression) {
        builder.append("( * ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(NegationExpression expression) {
        builder.append("( - ");
        expression.getSubexpression().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(SubtractionExpression expression) {
        builder.append("( - ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(VariableExpression expression) {
        if (expression.isArray()) {
            builder.append("( select ");
            builder.append(expression.getId());
            builder.append(' ');
            expression.getIndex().accept(this);
            builder.append(" )");
        } else {
            builder.append(expression.getId());
        }
    }

    @Override
    public void visit(AndExpression expression) {
        builder.append("( and ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(BooleanLiteralExpression expression) {
        if (expression.getValue()) {
            builder.append("true");
        } else {
            builder.append("false");
        }
    }

    @Override
    public void visit(EqualExpression expression) {
        builder.append("( = ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(GreaterEqualExpression expression) {
        builder.append("( >= ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(GreaterThanExpression expression) {
        builder.append("( > ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(ImplicationExpression expression) {
        builder.append("( => ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(LessEqualExpression expression) {
        builder.append("( <= ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(LessThanExpression expression) {
        builder.append("( < ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(NotEqualExpression expression) {
        builder.append("(not ( = ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" ) )");
    }

    @Override
    public void visit(NotExpression expression) {
        builder.append("( not ");
        expression.getSubexpression().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(OrExpression expression) {
        builder.append("( or ");
        expression.getLeft().accept(this);
        builder.append(' ');
        expression.getRight().accept(this);
        builder.append(" )");
    }

    @Override
    public void visit(RelationExpression expression) {
        if (expression.getArguments().size() == 0) {
            builder.append(expression.getIdentifier());
        } else {
            builder.append("( ");
            builder.append(expression.getIdentifier());
            for (Expression argument : expression.getArguments()) {
                argument.accept(this);
                builder.append(' ');
            }
            builder.append(" )");
        }
    }
}
