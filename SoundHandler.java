/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/
import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;

public class SoundHandler {
	

	
	
	    public static void RunMusic(String path) {
	        try {
	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
	            Clip clip = AudioSystem.getClip();
	            clip.open(inputStream);
	            clip.loop(Clip.LOOP_CONTINUOUSLY);
	        }
	        catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        catch ( LineUnavailableException e){
	            e.printStackTrace();
	        }
	    }
	}

