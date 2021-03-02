import javax.swing.*;
import java.awt.*;

public class TextEditor {
    private static JTextArea textArea;
    private static JFrame frame;
    private static JTextField metadataOfTextArea;

    private TextEditor() {
        textArea = EditorTextArea.createTextArea();

        createFrame();

        frame.setVisible(true);

        while(frame.isVisible()) {
            String font = FontSelector.getFont();

            textArea.setFont(new Font(font, Font.PLAIN, 12));
            metadataOfTextArea.setText(countWords() + " words");
        }
    }

    private static void createFrame() {
        JMenuBar menuBar = EditorMenuBar.createMenuBar(textArea);
        frame = new JFrame("Text Editor");

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(menuBar);
    }

    private static void createContentPane() {
        JScrollPane scrollPane =  new JScrollPane(textArea);
        Container contentPane = frame.getContentPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(metadataOfTextArea, BorderLayout.PAGE_END);
    }

    private static void createMetadataOfTextArea() {
        metadataOfTextArea = new JTextField();
        metadataOfTextArea.setHorizontalAlignment(SwingConstants.RIGHT);
        metadataOfTextArea.setBackground(Color.lightGray);
    }

    private static int countWords() {
        int numberOfWords = 0;
        String[] wordsSplitByWhiteSpace = textArea.getText().split("\\s+");
            numberOfWords += wordsSplitByWhiteSpace.length;

        return numberOfWords;
    }

    public static void main(String[] args) {
        new TextEditor();
    }

}
