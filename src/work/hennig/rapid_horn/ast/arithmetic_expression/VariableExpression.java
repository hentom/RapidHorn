package work.hennig.rapid_horn.ast.arithmetic_expression;

import java.util.Optional;

public class VariableExpression extends ArithmeticExpression {

    private String id;
    private Optional<ArithmeticExpression> index;

    public VariableExpression(String id) {
        this.id = id;
        this.index = Optional.empty();
    }

    public VariableExpression(String id, ArithmeticExpression index) {
        this.id = id;
        this.index = Optional.of(index);
    }

    public String getId() {
        return id;
    }

    public boolean isArray() {
        return index.isPresent();
    }

    public ArithmeticExpression getIndex() {
        return index.get();
    }

    @Override
    public void accept(ArithmeticExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
