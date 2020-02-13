package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

import java.util.Optional;

public class VariableExpression extends Expression {

    private String id;
    private Optional<Expression> index;

    public VariableExpression(String id) {
        this.id = id;
        this.index = Optional.empty();
    }

    public VariableExpression(String id, Expression index) {
        this.id = id;
        this.index = Optional.of(index);
    }

    public String getId() {
        return id;
    }

    public boolean isArray() {
        return index.isPresent();
    }

    public Expression getIndex() {
        return index.get();
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
