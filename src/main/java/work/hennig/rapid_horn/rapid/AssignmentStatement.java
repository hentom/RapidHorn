package work.hennig.rapid_horn.rapid;

import work.hennig.rapid_horn.rapid.expression.Expression;

import java.util.Optional;

public class AssignmentStatement extends Statement {

    private String id;
    private Optional<Expression> index;
    private Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.index = Optional.empty();
        this.expression = expression;
    }

    public AssignmentStatement(String id, Expression index, Expression expression) {
        this.id = id;
        this.index = Optional.of(index);
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
