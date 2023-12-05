package tetris;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    File audioFile;
    AudioInputStream audioStream;
    Clip clip;

    static AudioPlayer main_theme;
    static AudioPlayer game_over;

    public AudioPlayer (String path, int n) {
        try {

            // Open an audio input stream
            audioFile = new File(path);
            audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get a clip resource
            clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream
            clip.open(audioStream);

            if (n == 0) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background music indefinitely
            }
            else {clip.loop(n);}

            // Start playing the background music
            clip.start();



        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void reset(){
        clip.stop();
        clip.setFramePosition(0);
    }
}

