package umu.tds.componente;

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
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import umu.tds.manejador.AppMusic;
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
	public void pauseCancion() {
		if(mediaPlayer != null) mediaPlayer.pause();
	}
	
	public void stopCancion() {
		if(mediaPlayer != null) mediaPlayer.stop();
	}
	
	
	public void actualizarCancion(String url, Duration time, double volume) {
		System.out.println("time " + time.toSeconds());
		if(mediaPlayer != null && !mediaPlayer.getStatus().equals(Status.PLAYING)) {
			System.out.println("ASASD");
			mediaPlayer.setStartTime(time); 
			mediaPlayer.setVolume(volume/100);
			mediaPlayer.play();
		}
	}
	
	public void playCancion(String url, Duration time, double volume,boolean nextOrLast) {
		Media media = null;
		com.sun.javafx.application.PlatformImpl.startup(() -> {
		});
		
		if(mediaPlayer != null && mediaPlayer.getStatus().equals(Status.PAUSED) && !nextOrLast) {
			mediaPlayer.setStartTime(time);  
			mediaPlayer.setVolume(volume/100);
			mediaPlayer.play();
		
		}else {
			if(mediaPlayer != null && mediaPlayer.getStatus().equals(Status.PLAYING)) mediaPlayer.stop();
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

			AppMusic.getUnicaInstancia().anadirRepro(url.substring(url.lastIndexOf('-')+2, url.lastIndexOf('.')));
			AppMusic.getUnicaInstancia().anadirReciente(url.substring(url.lastIndexOf('-')+2, url.lastIndexOf('.')));
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setVolume(volume/100);
			mediaPlayer.play();
			
		}
		
	}
	
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
}
