package work.hennig.rapid_horn.rapid;

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

    public Declaration(DeclarationStatement statement) {
        this.constant = statement.isConstant();
        this.type = statement.getType();
        this.id = statement.getId();
        this.array = statement.isArray();
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

    public void accept(RapidVisitor visitor) {
        visitor.visit(this);
    }
}
