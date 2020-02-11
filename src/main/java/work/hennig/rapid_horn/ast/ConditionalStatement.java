package work.hennig.rapid_horn.ast;

import work.hennig.rapid_horn.ast.boolean_expression.BooleanExpression;

import java.util.List;
import java.util.Optional;

public class ConditionalStatement extends Statement {

    private BooleanExpression condition;
    private List<Statement> ifBlock;
    private Optional<List<Statement>> elseBlock;

    public ConditionalStatement(BooleanExpression condition, List<Statement> ifBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = Optional.empty();
    }

    public ConditionalStatement(BooleanExpression condition, List<Statement> ifBlock, List<Statement> elseBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = Optional.of(elseBlock);
    }

    public BooleanExpression getCondition() {
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
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
