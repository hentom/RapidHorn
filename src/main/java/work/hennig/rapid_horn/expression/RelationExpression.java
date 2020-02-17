package work.hennig.rapid_horn.expression;

import java.util.List;

public class RelationExpression extends Expression {

    private String identifier;
    private List<Expression> arguments;

    public RelationExpression(String identifier, List<Expression> arguments) {
        this.identifier = identifier;
        this.arguments = arguments;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
