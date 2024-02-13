package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.lang.reflect.Field;

public class music {
    public void musicplay(String musicpath) {

        try {
            File musicpath1 = new File(musicpath);
            if (!musicpath1.exists()) {
                musicpath1.mkdirs();
            }
            if (musicpath1.exists()) {
                AudioInputStream audioplay = AudioSystem.getAudioInputStream(musicpath1);
                Clip c = AudioSystem.getClip();
                System.out.println("音乐文件导入成功！");
                c.open(audioplay);
                c.start();
                c.loop(c.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            System.out.println("音乐文件导入失败" + e.getMessage());
        }
    }
}
