package work.hennig.rapid_horn.parser.scanner;

public class Scanner {

    private String input;
    private Token cache;

    public Scanner(String input) {
        this.input = input;
        this.cache = null;
    }

    public Token nextToken() {
        // use cached token from the stack, whenever possible
        if (cache != null) {
            Token tmp = cache;
            cache = null;
            return tmp;
        }

        // skip whitespace characters
        input.trim();

        if (input.length() > 0) {
            // check for keywords, boolean literals and type identifiers, assume identifier for other alphabetic input
            if (Character.isAlphabetic(input.charAt(0))) {
                if (input.startsWith("func")) {
                    input = input.substring("func".length());
                    return new Token(TokenType.KEY_FUNC, "func");
                } else if (input.startsWith("if")) {
                    input = input.substring("if".length());
                    return new Token(TokenType.KEY_IF, "if");
                } else if (input.startsWith("else")) {
                    input = input.substring("else".length());
                    return new Token(TokenType.KEY_ELSE, "else");
                } else if (input.startsWith("while")) {
                    input = input.substring("while".length());
                    return new Token(TokenType.KEY_WHILE, "while");
                } else if (input.startsWith("skip")) {
                    input = input.substring("skip".length());
                    return new Token(TokenType.KEY_SKIP, "skip");
                } else if (input.startsWith("true")) {
                    input = input.substring("true".length());
                    return new Token(TokenType.LIT_TRUE, "true");
                } else if (input.startsWith("false")) {
                    input = input.substring("false".length());
                    return new Token(TokenType.LIT_FALSE, "false");
                } else if (input.startsWith("Int")) {
                    input = input.substring("Int".length());
                    return new Token(TokenType.TYPE_INT, "Int");
                } else if (input.startsWith("Bool")) {
                    input = input.substring("Bool".length());
                    return new Token(TokenType.TYPE_BOOL, "Bool");
                } else if (input.startsWith("mod")) {
                    input = input.substring("mod".length());
                    return new Token(TokenType.OP_MODULO, "mod");
                } else {
                    int pos = 0;
                    while (pos < input.length() && Character.isAlphabetic(input.charAt(pos))) {
                        pos++;
                    }
                    String id = input.substring(0, pos);
                    input = input.substring(pos);
                    return new Token(TokenType.IDENTIFIER, id);
                }
            }

            // check for integer literals next
            if (Character.isDigit(input.charAt(0))) {
                int pos = 0;
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    pos++;
                }
                String literal = input.substring(0, pos);
                input = input.substring(pos);
                return new Token(TokenType.LIT_INT, literal);
            }

            // check for operators and symbols
            boolean nextAvailable = (input.length() >= 2);
            switch (input.charAt(0)) {
                case '+':
                    input = input.substring("+".length());
                    return new Token(TokenType.OP_PLUS, "+");
                case '-':
                    input = input.substring("-".length());
                    return new Token(TokenType.OP_MINUS, "-");
                case '*':
                    input = input.substring("*".length());
                    return new Token(TokenType.OP_MULTIPLICATION, "*");
                case '>':
                    if (nextAvailable && input.charAt(1) == '=') {
                        input = input.substring(">=".length());
                        return new Token(TokenType.OP_GREATER_THAN_OR_EQUAL, ">=");
                    } else {
                        input = input.substring(">".length());
                        return new Token(TokenType.OP_GREATER_THAN, ">");
                    }
                case '<':
                    if (nextAvailable && input.charAt(1) == '=') {
                        input = input.substring("<=".length());
                        return new Token(TokenType.OP_LESS_THAN_OR_EQUAL, "<=");
                    } else {
                        input = input.substring("<".length());
                        return new Token(TokenType.OP_LESS_THAN, "<");
                    }
                case '=':
                    if (nextAvailable && input.charAt(1) == '=') {
                        input = input.substring("==".length());
                        return new Token(TokenType.OP_EQUAL, "==");
                    } else {
                        input = input.substring("=".length());
                        return new Token(TokenType.OP_ASSIGN, "=");
                    }
                case '!':
                    if (nextAvailable && input.charAt(1) == '=') {
                        input = input.substring("!=".length());
                        return new Token(TokenType.OP_NOT_EQUAL, "!=");
                    } else {
                        input = input.substring("!".length());
                        return new Token(TokenType.OP_NEG, "!");
                    }
                case '&':
                    if (nextAvailable && input.charAt(1) == '&') {
                        input = input.substring("&&".length());
                        return new Token(TokenType.OP_AND, "&&");
                    }
                    break;
                case '|':
                    if (nextAvailable && input.charAt(1) == '|') {
                        input = input.substring("||".length());
                        return new Token(TokenType.OP_OR, "||");
                    }
                    break;
                case '(':
                    input = input.substring("(".length());
                    return new Token(TokenType.SYM_LEFT_PARENTHESIS, "(");
                case ')':
                    input = input.substring(")".length());
                    return new Token(TokenType.SYM_RIGHT_PARENTHESIS, ")");
                case '[':
                    input = input.substring("[".length());
                    return new Token(TokenType.SYM_LEFT_SQUARE_BRACKET, "[");
                case ']':
                    input = input.substring("]".length());
                    return new Token(TokenType.SYM_RIGHT_SQUARE_BRACKET, "]");
                case '{':
                    input = input.substring("{".length());
                    return new Token(TokenType.SYM_LEFT_CURLY_BRACKET, "{");
                case '}':
                    input = input.substring("}".length());
                    return new Token(TokenType.SYM_RIGHT_CURLY_BRACKET, "}");
                case ';':
                    input = input.substring(";".length());
                    return new Token(TokenType.SYM_SEMICOLON, ";");
            }
            System.err.println("ERROR: (scanner) unrecognized token starting with \'" + input.charAt(0) + "\'");
            System.exit(1);
        }

        return new Token(TokenType.EOF, "");
    }

    public void pushToken(Token token) {
        if (cache != null) {
            // TODO: use custom exception
            throw new UnsupportedOperationException();
        }
        cache = token;
    }
}
