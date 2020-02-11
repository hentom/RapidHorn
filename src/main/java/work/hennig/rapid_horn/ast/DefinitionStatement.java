package work.hennig.rapid_horn.ast;

import work.hennig.rapid_horn.ast.arithmetic_expression.ArithmeticExpression;

public class DefinitionStatement extends Statement {

    private DeclarationStatement declaration;
    private ArithmeticExpression expression;

    public DefinitionStatement(DeclarationStatement declaration, ArithmeticExpression expression) {
        this.declaration = declaration;
        this.expression = expression;
    }

    public DeclarationStatement getDeclaration() {
        return declaration;
    }

    public ArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
