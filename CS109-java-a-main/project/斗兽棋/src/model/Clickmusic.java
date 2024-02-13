package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Clickmusic {
    public void clickplay(String musicpath){

        try {
            File musicpath1=new File(musicpath);
            if(!musicpath1.exists()) {
                musicpath1.mkdirs();
            }
            if(musicpath1.exists()){
                AudioInputStream audioplay= AudioSystem.getAudioInputStream(musicpath1);
                Clip c=AudioSystem.getClip();
                System.out.println("音乐文件导入成功！");
                c.open(audioplay);
                c.start();
                int loop=0;
                c.loop(loop);
            }
        }catch (Exception e){
            System.out.println("音乐文件导入失败" + e.getMessage());
        }
    }
}
