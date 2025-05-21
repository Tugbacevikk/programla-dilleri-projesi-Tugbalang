
import java.util.*;
import java.util.regex.*;

public class Lexer {
    public static class Token {
        public String type;
        public String value;

        public Token(String type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + type + ": '" + value + "']";
        }
    }

    private static final Map<String, String> tokenPatterns = new LinkedHashMap<>();

    static {
        tokenPatterns.put("KEYWORD", "\\b(belirle|yaz|eger|ise|dongu|iken)\\b");
        tokenPatterns.put("NUMBER", "\\b[0-9]+\\b");
        tokenPatterns.put("IDENTIFIER", "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b");
        tokenPatterns.put("OPERATOR", "[=+\\-*/><]");
        tokenPatterns.put("DELIMITER", "[;{}()]");
        tokenPatterns.put("COMMENT", "#.*");
        tokenPatterns.put("WHITESPACE", "\\s+");  // işlenmeyecek
    }

    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        String remaining = input;

        while (!remaining.isEmpty()) {
            boolean matched = false;
            for (Map.Entry<String, String> entry : tokenPatterns.entrySet()) {
                Pattern pattern = Pattern.compile("^" + entry.getValue());
                Matcher matcher = pattern.matcher(remaining);
                if (matcher.find()) {
                    String value = matcher.group();
                    if (!entry.getKey().equals("WHITESPACE")) {
                        tokens.add(new Token(entry.getKey(), value));
                    }
                    remaining = remaining.substring(value.length());
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                throw new RuntimeException("Tanımsız token: " + remaining);
            }
        }

        return tokens;
    }

    // Test etmek için ana fonksiyon
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        String kod = """
            # Bu bir yorum satırıdır
            belirle x = 5;
            yaz x + 1;
            eger x > 3 ise {
                yaz x;
            }
            """;

        List<Token> tokens = lexer.tokenize(kod);
        for (Token t : tokens) {
            System.out.println(t);
        }
    }
}
