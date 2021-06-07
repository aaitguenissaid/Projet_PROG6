package Vue;

import Global.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class EffetsSonores {
    Clip clip1;
    Clip clip2;
    boolean on;
    public EffetsSonores() {
        on =Boolean.parseBoolean(Configuration.instance().get(Configuration.SON_ON))    ;
        try {
            URL url1 = Configuration.chargeURL(Configuration.instance().lis(Configuration.SOUND_1));
            AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(url1);
            URL url2 = Configuration.chargeURL(Configuration.instance().lis(Configuration.SOUND_2));
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(url2);
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
    public void deisabel_enabel_son(){
        on=!on;
    }
    public boolean getSonState(){
        return on;
    }
    public void moveStart(){
        if(on) {
            if (clip2.isActive()) clip2.stop();
            clip1.setFramePosition(0);
            clip1.start();
        }
    }
    public void moveEnd(){
        if(on){
            if (clip1.isActive()) clip1.stop();
            clip2.setFramePosition(0);
            clip2.start();
        }
    }
}
