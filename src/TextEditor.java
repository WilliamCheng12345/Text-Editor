import javax.swing.*;
import java.awt.*;

public class TextEditor {
    private static JTextArea textArea;
    private static JFrame frame;
    private static JTextField metadataOfTextArea;

    private TextEditor() {
        textArea = new JTextArea();

        createFrame();
        createContentPane();

        frame.setVisible(true);

        while (frame.isVisible()) {
            metadataOfTextArea.setText(countWords() + " words");
        }
    }

    private static void createFrame() {
        JMenuBar menuBar = MenuBar.createMenuBar(textArea);
        frame = new JFrame("Text Editor");

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(menuBar);
    }

    private static void createContentPane() {
        JScrollPane scrollPane = new JScrollPane(textArea);
        Container contentPane = frame.getContentPane();

        contentPane.add(scrollPane, BorderLayout.CENTER);
        createMetadataOfTextArea();
        contentPane.add(metadataOfTextArea, BorderLayout.PAGE_END);
    }

    private static void createMetadataOfTextArea() {
        metadataOfTextArea = new JTextField();

        metadataOfTextArea.setHorizontalAlignment(SwingConstants.RIGHT);
        metadataOfTextArea.setBackground(Color.lightGray);
    }

    private static int countWords() {
        int numberOfWords = 0;

        // Need to check if length is 0 because String split will count an empty
        if (textArea.getText().length() != 0) {
            String[] wordsSplitByWhiteSpace = textArea.getText().split("\\s+");
            numberOfWords += wordsSplitByWhiteSpace.length;
        }

        return numberOfWords;
    }

    static JTextArea getTextArea() {
        return textArea;
    }

    public static void main(String[] args) {
        new TextEditor();
    }

}
