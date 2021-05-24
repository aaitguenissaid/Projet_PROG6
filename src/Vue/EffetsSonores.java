package Vue;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class EffetsSonores {
    Clip clip1;
    Clip clip2;
    public EffetsSonores() {
        try {
            AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new File("ressources/sounds/1.wav").getAbsoluteFile());
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File("ressources/sounds/2.wav").getAbsoluteFile());
            clip1 = AudioSystem.getClip();
            clip2 = AudioSystem.getClip();
            clip1.open(audioInputStream1);
            clip2.open(audioInputStream2);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void moveStart(){
        if (clip2.isActive()) clip2.stop();
        clip1.setFramePosition(0);
        clip1.start();
    }
    public void moveEnd(){
        if (clip1.isActive()) clip1.stop();
        clip2.setFramePosition(0);
        clip2.start();
    }
}
