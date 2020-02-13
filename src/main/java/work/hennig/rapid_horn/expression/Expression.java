package work.hennig.rapid_horn.expression;

import work.hennig.rapid_horn.rapid.Type;

public abstract class Expression {

    public abstract void accept(ExpressionVisitor visitor);
}
