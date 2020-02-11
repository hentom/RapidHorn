package work.hennig.rapid_horn.ast;

import java.util.LinkedList;
import java.util.List;

public class Function {

    private String id;
    private List<DeclarationStatement> parameters;
    private List<Statement> block;

    public Function(String id, List<Statement> block) {
        this.id = id;
        this.parameters = new LinkedList<>();
        this.block = block;
    }

    public Function(String id, List<DeclarationStatement> parameters, List<Statement> block) {
        this.id = id;
        this.parameters = parameters;
        this.block = block;
    }

    public String getId() {
        return id;
    }

    public List<DeclarationStatement> getParameters() {
        return parameters;
    }

    public List<Statement> getBlock() {
        return block;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
