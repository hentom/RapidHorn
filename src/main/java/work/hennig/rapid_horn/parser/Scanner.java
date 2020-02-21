package work.hennig.rapid_horn.parser;

public class Scanner {

    private String input;
    private Token buffer;
    private int lineNumber;

    public Scanner(String input) {
        this.input = input;
        this.buffer = null;
        this.lineNumber = 1;
    }

    public Scanner(Scanner scanner) {
        this.input = scanner.input; // String objects are immutable
        this.buffer = scanner.buffer;
    }

    public Token nextToken() {
        // use cached token from the stack, whenever possible
        if (buffer != null) {
            Token tmp = buffer;
            buffer = null;
            return tmp;
        }

        // skip whitespace characters
        int pos = 0;
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            if (input.charAt(pos) == '\n') {
                lineNumber++;
            }
            pos++;
        }
        input = input.substring(pos);

        // skip rest of line, when '//' is found
        if (input.startsWith("//")) {
            int eol = 0;
            while (eol < input.length() && input.charAt(eol) != '\n') {
                eol++;
            }
            input = input.substring(eol);
            lineNumber++;
            return nextToken();
        }

        if (input.length() > 0) {
            // check for keywords, boolean literals and type identifiers, assume identifier for other alphabetic input
            if (Character.isAlphabetic(input.charAt(0))) {
                if (startsWithKeyword("const")) {
                    input = input.substring("const".length());
                    return new Token(TokenType.KEY_CONST, "const");
                } else if (startsWithKeyword("func")) {
                    input = input.substring("func".length());
                    return new Token(TokenType.KEY_FUNC, "func");
                } else if (startsWithKeyword("if")) {
                    input = input.substring("if".length());
                    return new Token(TokenType.KEY_IF, "if");
                } else if (startsWithKeyword("else")) {
                    input = input.substring("else".length());
                    return new Token(TokenType.KEY_ELSE, "else");
                } else if (startsWithKeyword("while")) {
                    input = input.substring("while".length());
                    return new Token(TokenType.KEY_WHILE, "while");
                } else if (startsWithKeyword("skip")) {
                    input = input.substring("skip".length());
                    return new Token(TokenType.KEY_SKIP, "skip");
                } else if (startsWithKeyword("assume")) {
                    input = input.substring("assume".length());
                    return new Token(TokenType.KEY_ASSUME, "assume");
                } else if (startsWithKeyword("assert")) {
                    input = input.substring("assert".length());
                    return new Token(TokenType.KEY_ASSERT, "assert");
                } else if (startsWithKeyword("true")) {
                    input = input.substring("true".length());
                    return new Token(TokenType.LIT_TRUE, "true");
                } else if (startsWithKeyword("false")) {
                    input = input.substring("false".length());
                    return new Token(TokenType.LIT_FALSE, "false");
                } else if (startsWithKeyword("Int")) {
                    input = input.substring("Int".length());
                    return new Token(TokenType.TYPE_INT, "Int");
                } else if (startsWithKeyword("Bool")) {
                    input = input.substring("Bool".length());
                    return new Token(TokenType.TYPE_BOOL, "Bool");
                } else if (startsWithKeyword("mod")) {
                    input = input.substring("mod".length());
                    return new Token(TokenType.OP_MODULO, "mod");
                } else {
                    pos = 0;
                    while (pos < input.length() && Character.isLetterOrDigit(input.charAt(pos))) {
                        pos++;
                    }
                    String id = input.substring(0, pos);
                    input = input.substring(pos);
                    return new Token(TokenType.IDENTIFIER, id);
                }
            }

            // check for integer literals next
            if (Character.isDigit(input.charAt(0))) {
                pos = 0;
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
                case ',':
                    input = input.substring(",".length());
                    return new Token(TokenType.SYM_COMMA, ",");
                case ';':
                    input = input.substring(";".length());
                    return new Token(TokenType.SYM_SEMICOLON, ";");
            }
            throw new ParserException("unrecognized token starting with \'" + input.charAt(0) + "\' in line " +
                    lineNumber);
        }

        return new Token(TokenType.EOF, "");
    }

    private boolean startsWithKeyword(String keyword) {
        if (keyword.length() < input.length()) {
            return (input.startsWith(keyword) && !Character.isAlphabetic(input.charAt(keyword.length())));
        } else {
            return input.startsWith(keyword);
        }
    }

    public Token pushToken(Token token) {
        if (buffer != null) {
            throw new ParserException("scanner token buffer overflow");
        }
        buffer = token;
        return buffer;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
