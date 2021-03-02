import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class OperateFileMenu implements ActionListener {
    private static JMenu operateFileMenu;
    private static JTextArea textArea;

    private OperateFileMenu(JTextArea textArea) {
        operateFileMenu = new JMenu("File");
        OperateFileMenu.textArea = textArea;

        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem newFile = new JMenuItem("New");

        operateFileMenu.add(open);
        operateFileMenu.add(save);
        operateFileMenu.add(newFile);

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
        int stateOfFileChooserDialog = fileChooser.showOpenDialog(null);

        if(stateOfFileChooserDialog == JFileChooser.APPROVE_OPTION) {
            File currentFile = fileChooser.getSelectedFile();

            try {
                FileReader fileReader = new FileReader(currentFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String currentLine = bufferedReader.readLine();
                String text = "";

                while(currentLine != null) {
                    text = text + currentLine + "\n";
                    currentLine = bufferedReader.readLine();
                }

                textArea.setText(text);
            } catch(Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
        }
    }

    private static void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int stateOfFileChooserDialog = fileChooser.showSaveDialog(null);

        if(stateOfFileChooserDialog == JFileChooser.APPROVE_OPTION) {
            File currentFile = fileChooser.getSelectedFile();

            try {
                FileWriter fileWriter = new FileWriter(currentFile, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(textArea.getText());
                bufferedWriter.close();
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
        }
    }

    static JMenu createOperateFileMenu(JTextArea textArea) {
        if(operateFileMenu == null) {
            new OperateFileMenu(textArea);
        }

        return operateFileMenu;
    }
}
