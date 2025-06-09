package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	Clip clip;
	URL sound[] = new URL[30];
	
	public Sound() {
		sound[0] = getClass().getResource("/sound/BeepBox-Song.wav");
	}
	
	public void setMusicFile(int i) {
		
		try {
		AudioInputStream input = AudioSystem.getAudioInputStream(sound[i]);
		clip = AudioSystem.getClip();
		clip.open(input);
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
