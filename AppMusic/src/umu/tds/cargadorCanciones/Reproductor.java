package umu.tds.cargadorCanciones;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import umu.tds.ventanas.Principal;

public class Reproductor {
	private MediaPlayer mediaPlayer;
	private String tempPath;
	private String binPath;
	
	public Reproductor() {
		mediaPlayer = null;
		binPath = Principal.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		tempPath = binPath.replace("/bin", "/temp");
	}
	public void stopCancion() {
		if(mediaPlayer != null) mediaPlayer.stop();
	}
	

	public void playCancion(String url) {
		Media media = null;
		if(mediaPlayer!= null ) mediaPlayer.stop(); 
		com.sun.javafx.application.PlatformImpl.startup(() -> {
		});
		if(url.startsWith("http")) {
		URL uri = null;
		try {
			uri = new URL(url);

			System.setProperty("java.io.tmpdir", tempPath);
			Path mp3 = Files.createTempFile("now-playing", ".mp3");
			
			System.out.println(mp3.getFileName());
			try (InputStream stream = uri.openStream()) {
				Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
			}
			System.out.println("finished-copy: " + mp3.getFileName());
	
			media = new Media(mp3.toFile().toURI().toString());
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}else {
			File f = new File(url);
			media = new Media(f.toURI().toString());
		}
		
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

		
	}
	
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
}
