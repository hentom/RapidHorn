package work.hennig.rapid_horn.parser;

public class Token {

    private TokenType type;
    private String content;

    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public TokenType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
