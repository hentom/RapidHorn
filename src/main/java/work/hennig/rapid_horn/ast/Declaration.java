package work.hennig.rapid_horn.ast;

public class Declaration {

    private boolean constant;
    private Type type;
    private String id;
    private boolean array;

    public Declaration(boolean constant, Type type, String id, boolean array) {
        this.constant = constant;
        this.type = type;
        this.array = array;
        this.id = id;
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

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
