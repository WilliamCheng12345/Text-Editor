import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;


public class MusicMenu implements ActionListener {
    private static JMenu musicMenu;

    private MusicMenu() {
        musicMenu = new JMenu("Music");

        JMenuItem start = new JMenuItem("Start");
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem resume = new JMenuItem("Resume");
        JMenuItem stop = new JMenuItem("Stop");

        musicMenu.add(start);
        musicMenu.add(pause);
        musicMenu.add(resume);
        musicMenu.add(stop);

        start.addActionListener(this);
        pause.addActionListener(this);
        resume.addActionListener(this);
        stop.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        switch (command) {
            case "Start":
                startMusic();
                break;
            case "Pause":
                MusicPlayer.pause();
                break;
            case "Resume":
                MusicPlayer.resume();
                break;
            case "Stop":
                MusicPlayer.close();
                break;
        }
    }

    private static void startMusic() {
        JFileChooser fileChooser = new JFileChooser();
        int stateOfFileChooserDialog = fileChooser.showOpenDialog(null);

        if (stateOfFileChooserDialog == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

                new MusicPlayer(bufferedInputStream);
                MusicPlayer.start();
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage());
            }
        }
    }

    static JMenu createMusicMenu() {
        if (musicMenu == null) {
            new MusicMenu();
        }

        return musicMenu;
    }
}
