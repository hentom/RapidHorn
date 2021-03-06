package work.hennig.rapid_horn.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

    @Test
    public void nextToken_EmptyScannerString() {
        Scanner scanner = new Scanner("");

        Token token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_Identifier() {
        Scanner scanner = new Scanner("funct");

        Token token = scanner.nextToken();
        assertEquals(TokenType.IDENTIFIER, token.getType());
        assertEquals("funct", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_IntegerLiteral() {
        Scanner scanner = new Scanner("1234");

        Token token = scanner.nextToken();
        assertEquals(TokenType.LIT_INT, token.getType());
        assertEquals("1234", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_TrueLiteral() {
        Scanner scanner = new Scanner("true");

        Token token = scanner.nextToken();
        assertEquals(TokenType.LIT_TRUE, token.getType());
        assertEquals("true", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_FalseLiteral() {
        Scanner scanner = new Scanner("false");

        Token token = scanner.nextToken();
        assertEquals(TokenType.LIT_FALSE, token.getType());
        assertEquals("false", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_ConstKeyword() {
        Scanner scanner = new Scanner("const");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_CONST, token.getType());
        assertEquals("const", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_FuncKeyword() {
        Scanner scanner = new Scanner("func");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_FUNC, token.getType());
        assertEquals("func", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_IfKeyword() {
        Scanner scanner = new Scanner("if");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_IF, token.getType());
        assertEquals("if", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_ElseKeyword() {
        Scanner scanner = new Scanner("else");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_ELSE, token.getType());
        assertEquals("else", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_WhileKeyword() {
        Scanner scanner = new Scanner("while");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_WHILE, token.getType());
        assertEquals("while", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_SkipKeyword() {
        Scanner scanner = new Scanner("skip");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_SKIP, token.getType());
        assertEquals("skip", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_AssumeKeyword() {
        Scanner scanner = new Scanner("assume");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_ASSUME, token.getType());
        assertEquals("assume", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_AssertKeyword() {
        Scanner scanner = new Scanner("assert");

        Token token = scanner.nextToken();
        assertEquals(TokenType.KEY_ASSERT, token.getType());
        assertEquals("assert", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_PlusOperator() {
        Scanner scanner = new Scanner("+");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_PLUS, token.getType());
        assertEquals("+", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_MinusOperator() {
        Scanner scanner = new Scanner("-");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_MINUS, token.getType());
        assertEquals("-", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_MultiplicationOperator() {
        Scanner scanner = new Scanner("*");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_MULTIPLICATION, token.getType());
        assertEquals("*", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_ModuloOperator() {
        Scanner scanner = new Scanner("mod");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_MODULO, token.getType());
        assertEquals("mod", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_GreaterOperator() {
        Scanner scanner = new Scanner(">");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_GREATER_THAN, token.getType());
        assertEquals(">", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_GreaterEqualOperator() {
        Scanner scanner = new Scanner(">=");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_GREATER_THAN_OR_EQUAL, token.getType());
        assertEquals(">=", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_LessOperator() {
        Scanner scanner = new Scanner("<");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_LESS_THAN, token.getType());
        assertEquals("<", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_LessEqualOperator() {
        Scanner scanner = new Scanner("<=");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_LESS_THAN_OR_EQUAL, token.getType());
        assertEquals("<=", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_AssignOperator() {
        Scanner scanner = new Scanner("=");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_ASSIGN, token.getType());
        assertEquals("=", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_EqualOperator() {
        Scanner scanner = new Scanner("==");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_EQUAL, token.getType());
        assertEquals("==", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_NotEqualOperator() {
        Scanner scanner = new Scanner("!=");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_NOT_EQUAL, token.getType());
        assertEquals("!=", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_AndOperator() {
        Scanner scanner = new Scanner("&&");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_AND, token.getType());
        assertEquals("&&", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_OrOperator() {
        Scanner scanner = new Scanner("||");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_OR, token.getType());
        assertEquals("||", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_NegOperator() {
        Scanner scanner = new Scanner("!");

        Token token = scanner.nextToken();
        assertEquals(TokenType.OP_NEG, token.getType());
        assertEquals("!", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_LeftParenthesis() {
        Scanner scanner = new Scanner("(");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_LEFT_PARENTHESIS, token.getType());
        assertEquals("(", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_RightParenthesis() {
        Scanner scanner = new Scanner(")");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_RIGHT_PARENTHESIS, token.getType());
        assertEquals(")", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_LeftSquareBracket() {
        Scanner scanner = new Scanner("[");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_LEFT_SQUARE_BRACKET, token.getType());
        assertEquals("[", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_RightSquareBracket() {
        Scanner scanner = new Scanner("]");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_RIGHT_SQUARE_BRACKET, token.getType());
        assertEquals("]", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_LeftCurlyBracket() {
        Scanner scanner = new Scanner("{");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_LEFT_CURLY_BRACKET, token.getType());
        assertEquals("{", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_RightCurlyBracket() {
        Scanner scanner = new Scanner("}");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_RIGHT_CURLY_BRACKET, token.getType());
        assertEquals("}", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_Comma() {
        Scanner scanner = new Scanner(",");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_COMMA, token.getType());
        assertEquals(",", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_Semicolon() {
        Scanner scanner = new Scanner(";");

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_SEMICOLON, token.getType());
        assertEquals(";", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_IntType() {
        Scanner scanner = new Scanner("Int");

        Token token = scanner.nextToken();
        assertEquals(TokenType.TYPE_INT, token.getType());
        assertEquals("Int", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_BoolType() {
        Scanner scanner = new Scanner("Bool");

        Token token = scanner.nextToken();
        assertEquals(TokenType.TYPE_BOOL, token.getType());
        assertEquals("Bool", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void nextToken_Underscore_Fail() {
        Scanner scanner = new Scanner("_");

        assertThrows(UnsupportedOperationException.class, () -> {
            Token token = scanner.nextToken();
        });
    }

    @Test
    public void pushToken_EmptyScannerString_AddOneToken() {
        Scanner scanner = new Scanner("");
        scanner.pushToken(new Token(TokenType.SYM_SEMICOLON, ";"));

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_SEMICOLON, token.getType());
        assertEquals(";", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    @Test
    public void pushToken_NonEmptyScannerString_AddOneToken() {
        Scanner scanner = new Scanner("func");
        scanner.pushToken(new Token(TokenType.SYM_SEMICOLON, ";"));

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_SEMICOLON, token.getType());
        assertEquals(";", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.KEY_FUNC, token.getType());
        assertEquals("func", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }

    /*@Test
    public void pushToken_EmptyScannerString_AddTwoTokens() {
        Scanner scanner = new Scanner("");
        scanner.pushToken(new Token(TokenType.OP_PLUS, "+"));
        scanner.pushToken(new Token(TokenType.SYM_SEMICOLON, ";"));

        Token token = scanner.nextToken();
        assertEquals(TokenType.SYM_SEMICOLON, token.getType());
        assertEquals(";", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.OP_PLUS, token.getType());
        assertEquals("+", token.getContent());

        token = scanner.nextToken();
        assertEquals(TokenType.EOF, token.getType());
        assertEquals("", token.getContent());
    }*/
}