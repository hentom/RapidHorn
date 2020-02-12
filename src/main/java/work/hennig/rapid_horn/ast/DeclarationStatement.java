package work.hennig.rapid_horn.ast;

import work.hennig.rapid_horn.ast.arithmetic_expression.ArithmeticExpression;

import java.util.Optional;

public class DeclarationStatement extends Statement {

    private boolean constant;
    private Type type;
    private String id;
    private boolean array;
    private Optional<ArithmeticExpression> expression;

    public DeclarationStatement(Declaration declaration) {
        this.constant = declaration.isConstant();
        this.type = declaration.getType();
        this.array = declaration.isArray();
        this.id = declaration.getId();
        this.expression = Optional.empty();
    }

    public DeclarationStatement(Declaration declaration, ArithmeticExpression expression) {
        this.constant = declaration.isConstant();
        this.type = declaration.getType();
        this.array = declaration.isArray();
        this.id = declaration.getId();
        this.expression = Optional.of(expression);
    }

    public boolean isConstant() {
        return constant;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public boolean isArray() {
        return array;
    }

    public boolean hasExpression() {
        return expression.isPresent();
    }

    public ArithmeticExpression getExpression() {
        return expression.get();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
