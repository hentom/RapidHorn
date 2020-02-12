package work.hennig.rapid_horn.ast;

import work.hennig.rapid_horn.ast.arithmetic_expression.ArithmeticExpression;

public class AssignmentStatement extends Statement {

    private String id;
    private ArithmeticExpression expression;

    public AssignmentStatement(String id, ArithmeticExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public ArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
