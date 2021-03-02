import javax.swing.*;
import java.awt.*;

class EditorTextArea {
    private static JTextArea textArea;

    private EditorTextArea() {
        textArea = new JTextArea();

        textArea.setForeground(Color.green);
        textArea.setBackground(Color.darkGray);
    }

    static JTextArea createTextArea() {
        if(textArea == null) {
            new EditorTextArea();
        }

        return textArea;
    }
}
