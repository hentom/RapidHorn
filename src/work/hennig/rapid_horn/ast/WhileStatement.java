package work.hennig.rapid_horn.ast;

import work.hennig.rapid_horn.ast.boolean_expression.BooleanExpression;

import java.util.List;

public class WhileStatement extends Statement {

    private BooleanExpression condition;
    private List<Statement> block;

    public WhileStatement(BooleanExpression condition, List<Statement> block) {
        this.condition = condition;
        this.block = block;
    }

    public BooleanExpression getCondition() {
        return condition;
    }

    public List<Statement> getBlock() {
        return block;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
