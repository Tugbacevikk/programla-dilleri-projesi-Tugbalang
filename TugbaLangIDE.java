import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.List;

public class TugbaLangIDE {
    public static void main(String[] args) {
        JFrame pencere = new JFrame("TugbaLang IDE");
        JTextPane yaziAlani = new JTextPane();
        JScrollPane kaydir = new JScrollPane(yaziAlani);
        Lexer lexer = new Lexer();

        yaziAlani.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { renklendir(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { renklendir(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}

            private void renklendir() {
                SwingUtilities.invokeLater(() -> {
                    StyledDocument doc = yaziAlani.getStyledDocument();
                    Style defaultStyle = yaziAlani.addStyle("default", null);
                    StyleConstants.setForeground(defaultStyle, Color.BLACK);
                    doc.setCharacterAttributes(0, yaziAlani.getText().length(), defaultStyle, true);

                    try {
                        List<Lexer.Token> tokenler = lexer.tokenize(yaziAlani.getText());

                        int pozisyon = 0;
                        String tumMetin = yaziAlani.getText();

                        for (Lexer.Token token : tokenler) {
                            // Token'ın tam metindeki konumunu bul
                            int index = tumMetin.indexOf(token.value, pozisyon);
                            if (index == -1) continue;

                            Style stil = yaziAlani.addStyle(token.type, null);
                            switch (token.type) {
                                case "KEYWORD":   StyleConstants.setForeground(stil, Color.BLUE); break;
                                case "NUMBER":    StyleConstants.setForeground(stil, Color.MAGENTA); break;
                                case "IDENTIFIER":StyleConstants.setForeground(stil, Color.BLACK); break;
                                case "OPERATOR":  StyleConstants.setForeground(stil, Color.RED); break;
                                case "DELIMITER": StyleConstants.setForeground(stil, Color.GRAY); break;
                                case "COMMENT":   StyleConstants.setForeground(stil, new Color(0, 128, 0)); break; // yeşil
                            }

                            doc.setCharacterAttributes(index, token.value.length(), stil, true);
                            pozisyon = index + token.value.length(); // bir sonraki arama için güncelle
                        }
                    } catch (Exception ex) {
                        System.out.println("Renklendirme hatası: " + ex.getMessage());
                    }
                });
            }
        });

        pencere.add(kaydir);
        pencere.setSize(800, 600);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setVisible(true);
    }
}

