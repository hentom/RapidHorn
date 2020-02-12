package work.hennig.rapid_horn.ast;

import java.util.LinkedList;
import java.util.List;

public class Function {

    private String id;
    private List<Declaration> parameters;
    private List<Statement> statements;

    public Function(String id, List<Declaration> parameters, List<Statement> statements) {
        this.id = id;
        this.parameters = parameters;
        this.statements = statements;
    }

    public String getId() {
        return id;
    }

    public List<Declaration> getParameters() {
        return parameters;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
