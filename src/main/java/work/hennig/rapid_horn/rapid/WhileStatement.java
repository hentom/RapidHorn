package work.hennig.rapid_horn.rapid;

import work.hennig.rapid_horn.expression.Expression;

import java.util.List;

public class WhileStatement extends Statement {

    private Expression condition;
    private List<Statement> block;

    public WhileStatement(Expression condition, List<Statement> block) {
        this.condition = condition;
        this.block = block;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getBlock() {
        return block;
    }

    @Override
    public void accept(RapidVisitor visitor) {
        visitor.visit(this);
    }
}
