package work.hennig.rapid_horn.rapid;

import work.hennig.rapid_horn.expression.Expression;

public class AssumeStatement extends Statement {

    private Expression condition;

    public AssumeStatement(Expression condition) {
        this.condition = condition;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public void accept(RapidVisitor visitor) {
        visitor.visit(this);
    }
}
