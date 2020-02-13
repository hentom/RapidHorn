package work.hennig.rapid_horn.rapid;

public enum Type {
    INTEGER,
    BOOLEAN;

    @Override
    public String toString() {
        switch (this) {
            case INTEGER:
                return "Int";
            case BOOLEAN:
                return "Bool";
        }
        throw new UnsupportedOperationException();
    }
}
