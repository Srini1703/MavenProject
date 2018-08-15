package CTS.MavenProject_TestCase;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.google.cloud.speech.v1p1beta1.RecognitionAudio;
import com.google.cloud.speech.v1p1beta1.RecognitionConfig;
import com.google.cloud.speech.v1p1beta1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1p1beta1.RecognizeResponse;
import com.google.cloud.speech.v1p1beta1.SpeechClient;
import com.google.cloud.speech.v1p1beta1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1p1beta1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import com.functionalLibrary.FunctionalLibrary;

import CTS.Utlities.OR_Reader;

import com.google.api.gax.paging.Page;
import com.google.auth.appengine.AppEngineCredentials;
import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestApplication {
	
	public static void main(String... args) throws Exception {
		
		Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("./resource/en-us");
        configuration.setDictionaryPath("./resource/cmudict-en-us.dict");
        configuration.setLanguageModelPath("./resource/en-us.lm.bin");
        configuration.setSampleRate(44100);
        

	StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
	InputStream stream = new FileInputStream(new File("./STT/okaybye.wav"));

        recognizer.startRecognition(stream);
	SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
	    System.out.format("Hypothesis: %s\n", result.getHypothesis());
	}
	recognizer.stopRecognition();
		/*GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Program Files (x86)\\Google\\Cloud SDK\\srini17031991-stt.json"))
		          .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
		    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

	    System.out.println("Buckets:");
	    Page<Bucket> buckets = storage.list();
	    for (Bucket bucket : buckets.iterateAll()) {
	      System.out.println(bucket.toString());
	    }*/
	    // Instantiates a client
		/*AudioFormat au = new AudioFormat(8000,16,1,false,false);
		//AudioFormat au = new AudioFormat(44100.0f, 16, 1, true, true);
		DataLine.Info in = new DataLine.Info(TargetDataLine.class, au);
		final TargetDataLine tl = (TargetDataLine)AudioSystem.getLine(in);
		tl.open();
		System.out.println("Reording Mode");
		
		tl.start();
		
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				AudioInputStream ai = new AudioInputStream(tl);
				File f = new File("./STT/audio.wav");
				try {
					AudioSystem.write(ai, AudioFileFormat.Type.WAVE, f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		th.start();
		Thread.sleep(8000);
		tl.stop();tl.close();
		System.out.println("ENDED");*/
		
	    /*try (SpeechClient speechClient = SpeechClient.create()) {

	      // The path to the audio file to transcribe
	      String fileName = "./STT/test.raw";

	      // Reads the audio file into memory
	      Path path = Paths.get(fileName);
	      byte[] data = Files.readAllBytes(path);
	      ByteString audioBytes = ByteString.copyFrom(data);

	      // Builds the sync recognize request
	      RecognitionConfig config = RecognitionConfig.newBuilder()
	          .setEncoding(AudioEncoding.LINEAR16)
	          .setSampleRateHertz(16000)
	          .setLanguageCode("en-US")
	          .build();
	      RecognitionAudio audio = RecognitionAudio.newBuilder()
	          .setContent(audioBytes)
	          .build();

	      // Performs speech recognition on the audio file
	      RecognizeResponse response = speechClient.recognize(config, audio);
	      List<SpeechRecognitionResult> results = response.getResultsList();

	      for (SpeechRecognitionResult result : results) {
	        // There can be several alternative transcripts for a given chunk of speech. Just use the
	        // first (most likely) one here.
	        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
	        System.out.printf("Transcription: %s%n", alternative.getTranscript());
	      }
	    }*/
	  }
	
	/*
	FunctionalLibrary driver;
	
	@BeforeSuite
	void initialize() throws Exception
	{
		OR_Reader.readOR();
	}
	

	@Test
	void MyTest() throws Exception
	{
		try {
			driver = new FunctionalLibrary("Chrome");
			driver.launchBrowser("C:\\Users\\srini\\git\\MavenProject\\MavenProject\\TTS and STT\\SpeechToText.html");
			if(driver.isDisplayed("TTSFrame")) {
				driver.switchToFrame("TTSFrame");
				driver.clearText("TTSsetText");
				Thread.sleep(1000);
				driver.sendkeys("TTSsetText", "Alexa,");
			}
			if(driver.isDisplayed("TTSbutton"))
				driver.clickElement("TTSbutton");
			driver.switchToParentWindow();
			if(driver.isDisplayed("TTSmicBtn"))
				driver.clickElement("TTSmicBtn");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		driver.killSession();
	}
	
*/}
