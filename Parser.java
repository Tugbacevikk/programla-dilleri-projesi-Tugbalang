import java.util.List;

public class Parser {
    private List<Lexer.Token> tokens;
    private int pos = 0;

    public Parser(List<Lexer.Token> tokens) {
        this.tokens = tokens;
    }

    public boolean parse() {
        try {
            while (pos < tokens.size()) {
                komut();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
            return false;
        }
    }

    private void komut() {
        Lexer.Token t = bak();

        if (t.value.equals("belirle")) {
            esles("KEYWORD", "belirle");
            esles("IDENTIFIER");
            esles("OPERATOR", "=");
            ifade();
            esles("DELIMITER", ";");
        }
        else if (t.value.equals("yaz")) {
            esles("KEYWORD", "yaz");
            ifade();
            esles("DELIMITER", ";");
        }
        else if (t.type.equals("COMMENT")) {
            pos++; // yorumu atla
        }
        else {
            throw new RuntimeException("Bilinmeyen komut: " + t.value);
        }
    }

    private void ifade() {
        esles("IDENTIFIER|NUMBER");
        while (pos < tokens.size() && bak().type.equals("OPERATOR")) {
            esles("OPERATOR");
            esles("IDENTIFIER|NUMBER");
        }
    }

    private Lexer.Token bak() {
        return tokens.get(pos);
    }

    private void esles(String beklenenTip) {
        Lexer.Token t = bak();
        if (!t.type.matches(beklenenTip)) {
            throw new RuntimeException("Beklenen: " + beklenenTip + ", bulundu: " + t.type + " (" + t.value + ")");
        }
        pos++;
    }

    private void esles(String beklenenTip, String beklenenDeger) {
        Lexer.Token t = bak();
        if (!t.type.equals(beklenenTip) || !t.value.equals(beklenenDeger)) {
            throw new RuntimeException("Beklenen: " + beklenenTip + "=" + beklenenDeger + ", bulundu: " + t);
        }
        pos++;
    }
}
