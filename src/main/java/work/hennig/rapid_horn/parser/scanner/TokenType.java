package work.hennig.rapid_horn.parser.scanner;

public enum TokenType {
    EOF("EOF"),
    IDENTIFIER("identifier"),

    // literals
    LIT_INT("integer literal"),
    LIT_TRUE("boolean literal \'true\'"),
    LIT_FALSE("boolean literal \'false\'"),

    // keywords
    KEY_CONST("keyword \'const\'"),
    KEY_FUNC("keyword \'func\'"),
    KEY_IF("keyword \'if\'"),
    KEY_ELSE("keyword \'else\'"),
    KEY_WHILE("keyword \'while\'"),
    KEY_SKIP("keyword \'skip\'"),

    // operators
    OP_PLUS("operator \'+\'"),
    OP_MINUS("operator \'-\'"),
    OP_MULTIPLICATION("operator \'*\'"),
    OP_MODULO("operator \'mod\'"),
    OP_GREATER_THAN("operator \'>\'"),
    OP_GREATER_THAN_OR_EQUAL("operator \'>=\'"),
    OP_LESS_THAN("operator \'<\'"),
    OP_LESS_THAN_OR_EQUAL("operator \'<=\'"),
    OP_ASSIGN("operator \'=\'"),
    OP_EQUAL("operator \'==\'"),
    OP_NOT_EQUAL("operator \'!=\'"),
    OP_AND("operator \'&&\'"),
    OP_OR("operator \'||\'"),
    OP_NEG("operator \'!\'"),

    // other symbols
    SYM_LEFT_PARENTHESIS("symbol \'(\'"),
    SYM_RIGHT_PARENTHESIS("symbol \')\'"),
    SYM_LEFT_SQUARE_BRACKET("symbol \'[\'"),
    SYM_RIGHT_SQUARE_BRACKET("symbol \']\'"),
    SYM_LEFT_CURLY_BRACKET("symbol \'{\'"),
    SYM_RIGHT_CURLY_BRACKET("symbol \'}\'"),
    SYM_COMMA("symbol \',\'"),
    SYM_SEMICOLON("symbol \';\'"),

    // type identifiers
    TYPE_INT("type identifier \'Int\'"),
    TYPE_BOOL("type identifier \'Bool\'");

    private String name;

    TokenType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
