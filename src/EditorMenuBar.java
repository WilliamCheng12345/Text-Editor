import javax.swing.*;

class EditorMenuBar {
    private static  JMenuBar menuBar;

    private EditorMenuBar(JTextArea textArea) {
        menuBar = new JMenuBar();

        JMenu editTextMenu = EditTextMenu.createEditTextMenu();
        JMenu operateFileMenu = OperateFileMenu.createOperateFileMenu(textArea);
        JMenu musicMenu = MusicMenu.createMusicMenu();
        JComboBox fontSelector = FontSelector.createFontSelector();

        menuBar.add(editTextMenu);
        menuBar.add(operateFileMenu);
        menuBar.add(musicMenu);
        menuBar.add(fontSelector);
    }

     static JMenuBar createMenuBar(JTextArea textArea) {
        if(menuBar == null) {
            new EditorMenuBar(textArea);
        }

        return menuBar;
    }
}
