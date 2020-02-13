package work.hennig.rapid_horn.rapid.expression;

import work.hennig.rapid_horn.rapid.Type;

public abstract class Expression {

    public abstract Type returnType();

    public abstract void accept(ExpressionVisitor visitor);
}
