package work.hennig.rapid_horn.ast.boolean_expression;

public class NotExpression extends BooleanExpression {

    private BooleanExpression subexpression;

    public NotExpression(BooleanExpression subexpression) {
        this.subexpression = subexpression;
    }

    public BooleanExpression getSubexpression() {
        return subexpression;
    }

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
