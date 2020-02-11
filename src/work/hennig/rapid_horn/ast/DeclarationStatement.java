package work.hennig.rapid_horn.ast;

public class DeclarationStatement extends Statement {

    private boolean constant;
    private Type type;
    private boolean array;
    private String id;

    public DeclarationStatement(boolean constant, Type type, boolean array, String id) {
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

    public boolean isArray() {
        return array;
    }

    public String getId() {
        return id;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
