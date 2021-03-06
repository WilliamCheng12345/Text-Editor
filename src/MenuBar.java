import javax.swing.*;

class MenuBar {
    private static JMenuBar menuBar;

    private MenuBar() {
        menuBar = new JMenuBar();

        JMenu editTextMenu = EditMenu.createEditMenu();
        JMenu operateFileMenu = FileMenu.createFileMenu();
        JMenu musicMenu = MusicMenu.createMusicMenu();

        menuBar.add(editTextMenu);
        menuBar.add(operateFileMenu);
        menuBar.add(musicMenu);
    }

    static JMenuBar createMenuBar(JTextArea textArea) {
        if (menuBar == null) {
            new MenuBar();
        }

        return menuBar;
    }
}
