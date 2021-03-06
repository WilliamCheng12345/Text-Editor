import javax.swing.*;
import java.io.BufferedInputStream;
import javazoom.jl.player.Player;

class MusicPlayer {
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
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Cannot open music file");
        }
    }

    static void start() {
        if (playerStatus == FINISHED) {
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

    /**
     * In order to pause, we change the status to PAUSED. However, we need
     * the thread playing music to stop. We thus use the wait() to make the thread
     * wait for resume() to be called.
     **/
    private static void play() {
        while (playerStatus != FINISHED) {
            try {
                if (!musicPlayer.play(1)) {
                    break;
                }
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Cannot play music file");
                break;
            }

            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Cannot play music file");
                        break;
                    }
                }

            }
        }
        close();
    }

    /**
     * When resume() is called by the main thread, the main thread calls notifyAll()
     * updates the status to PLAYING to have the thread playing music continue to execute.
     **/
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
        if(musicPlayer == null) {
            JOptionPane.showMessageDialog(null, "No music file is playing");
            return;
        }

        playerStatus = FINISHED;

        try {
            musicPlayer.close();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

}
