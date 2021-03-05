import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class FileMenu implements ActionListener {
    private static JMenu fileMenu;
    private static JTextArea textArea;

    private FileMenu() {
        fileMenu = new JMenu("File");
        textArea = TextEditor.textArea;

        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem newFile = new JMenuItem("New");

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(newFile);

        open.addActionListener(this);
        save.addActionListener(this);
        newFile.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        switch (command) {
            case "Open":
                openFile();
                break;
            case "Save":
                saveFile();
                break;
            case "New":
                textArea.setText("");
                break;
        }
    }

    private static void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int stateOfDialog = fileChooser.showOpenDialog(null);

        if(stateOfDialog == JFileChooser.APPROVE_OPTION) {
            File currentFile = fileChooser.getSelectedFile();

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFile));
                String currentLine = bufferedReader.readLine();
                String text = "";

                while(currentLine != null) {
                    text += currentLine + "\n";
                    currentLine = bufferedReader.readLine();
                }

                textArea.setText(text);
            } catch(Exception error) {
                JOptionPane.showMessageDialog(null, "Cannot open file");
            }
        }
    }

    private static void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int stateOfFileChooserDialog = fileChooser.showSaveDialog(null);

        if(stateOfFileChooserDialog == JFileChooser.APPROVE_OPTION) {
            File currentFile = fileChooser.getSelectedFile();

            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentFile));

                bufferedWriter.write(textArea.getText());
                bufferedWriter.close();
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Cannot save file");
            }
        }
    }

    static JMenu createFileMenu() {
        if(fileMenu == null) {
            new FileMenu();
        }

        return fileMenu;
    }
}
