package work.hennig.rapid_horn.parser.scanner;

public enum TokenType {
    EOF,
    IDENTIFIER,

    // literals
    LIT_INT,
    LIT_TRUE,
    LIT_FALSE,

    // keywords
    KEY_CONST,
    KEY_FUNC,
    KEY_IF,
    KEY_ELSE,
    KEY_WHILE,
    KEY_SKIP,

    // operators
    OP_PLUS,
    OP_MINUS,
    OP_MULTIPLICATION,
    OP_MODULO,
    OP_GREATER_THAN,
    OP_GREATER_THAN_OR_EQUAL,
    OP_LESS_THAN,
    OP_LESS_THAN_OR_EQUAL,
    OP_ASSIGN,
    OP_EQUAL,
    OP_NOT_EQUAL,
    OP_AND,
    OP_OR,
    OP_NEG,

    // other symbols
    SYM_LEFT_PARENTHESIS,
    SYM_RIGHT_PARENTHESIS,
    SYM_LEFT_SQUARE_BRACKET,
    SYM_RIGHT_SQUARE_BRACKET,
    SYM_LEFT_CURLY_BRACKET,
    SYM_RIGHT_CURLY_BRACKET,
    SYM_SEMICOLON,

    // type identifiers
    TYPE_INT,
    TYPE_BOOL;
}
