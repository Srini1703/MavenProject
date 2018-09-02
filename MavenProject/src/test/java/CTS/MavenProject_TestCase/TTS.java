package CTS.MavenProject_TestCase;

import java.io.File;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TTS {

	public static void main(String[] args) {
		 
	    try {
	      File file = new File("./STT/open_the_door.wav");
	      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
	      byte[] data=new byte[stream.available()];
	      System.out.println(stream.read(data));
	      System.out.println(Arrays.toString(data));
	      Clip clip = AudioSystem.getClip();
	      clip.open(stream);
	      System.out.println(clip.getFormat());
	      System.out.println(clip.getFrameLength());
	      System.out.println(clip.toString());
	 
	      // sleep to allow enough time for the clip to play
	      Thread.sleep(1500);
	  
	      stream.close();
	 
	    } catch (Exception ex) {
	      System.out.println(ex.getMessage());
	    }
	  }
//--USE the below LINK--
//https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/speech/cloud-client/src/main/java/com/example/speech/Recognize.java
}
