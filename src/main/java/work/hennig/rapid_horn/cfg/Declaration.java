package work.hennig.rapid_horn.cfg;

import java.util.Objects;

public class Declaration {

    private String identifier;
    private boolean isArray;

    public Declaration(String identifier, boolean isArray) {
        this.identifier = identifier;
        this.isArray = isArray;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isArray() {
        return isArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Declaration that = (Declaration) o;
        return isArray == that.isArray &&
                identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, isArray);
    }
}
