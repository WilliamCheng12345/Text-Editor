
import javax.swing.*;
import java.io.BufferedInputStream;
import javazoom.jl.player.Player;

class MusicPlayer  {
    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private static Player musicPlayer;
    private static final Object playerLock = new Object();
    private static int playerStatus = NOTSTARTED;

    MusicPlayer(BufferedInputStream currentFileInputStream) {
        try {
            musicPlayer = new Player(currentFileInputStream);
        }
        catch(Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    static void start() {
        if(playerStatus == FINISHED) {
            playerStatus = NOTSTARTED;
        }

        if (playerStatus == NOTSTARTED) {
            Runnable startOfPlayer = new Runnable() {
                @Override
                public void run() {
                    playerStatus = PLAYING;
                    play();
                }
            };
            Thread musicThread = new Thread(startOfPlayer);
            musicThread.start();
        }
    }

    private static void play() {
        while(playerStatus != FINISHED) {
            try {
                if (!musicPlayer.play(1)) {
                    break;
                }
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage());
                break;
            }

            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, error.getMessage());
                        break;
                    }
                }
            }
        }

        close();
    }

    static void resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
        }
    }

    static void pause() {
        if (playerStatus == PLAYING) {
            playerStatus = PAUSED;
        }
    }

    static void close() {
        playerStatus = FINISHED;

        try {
            musicPlayer.close();
        }
        catch(Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

}
