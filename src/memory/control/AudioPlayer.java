package memory.control;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * AudioPlayer class which can play a wav sound file.
 */
public class AudioPlayer implements LineListener {

    /**
     * this boolean indicates whether the playback completes or not.
     */
    boolean soundFileCompleted;

    /**
     * Play a given audio file with jafax_Clip.
     * @param audioFilePath Path of the audio file.
     */
    public void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();

            while (!soundFileCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            audioClip.close();

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Sound started.");

        } else if (type == LineEvent.Type.STOP) {
            soundFileCompleted = true;
            System.out.println("Sound completed.");
        }

    }
}
// HOW TO start Music:
//        String audioFilePath = "src/memory/music.wav";
//        AudioPlayerExample1 player = new AudioPlayerExample1();
//        player.play(audioFilePath);
