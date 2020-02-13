package work.hennig.rapid_horn.rapid;

import work.hennig.rapid_horn.expression.Expression;

import java.util.List;
import java.util.Optional;

public class ConditionalStatement extends Statement {

    private Expression condition;
    private List<Statement> ifBlock;
    private Optional<List<Statement>> elseBlock;

    public ConditionalStatement(Expression condition, List<Statement> ifBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = Optional.empty();
    }

    public ConditionalStatement(Expression condition, List<Statement> ifBlock, List<Statement> elseBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = Optional.of(elseBlock);
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getIfBlock() {
        return ifBlock;
    }

    public boolean hasElseBlock() {
        return elseBlock.isPresent();
    }

    public List<Statement> getElseBlock() {
        return elseBlock.get();
    }

    @Override
    public void accept(RapidVisitor visitor) {
        visitor.visit(this);
    }
}
