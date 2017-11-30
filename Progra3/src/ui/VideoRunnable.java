package ui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import logic.file.Video;

public class VideoRunnable implements Runnable{
    private JFXPanel panel;
    private Video video;
    private volatile boolean terminated;
    
    public VideoRunnable(JFXPanel panel, Video video) {
        this.panel = panel;
        this.video = video;
        this.terminated = false;
    }
    
    public void terminate(){
        this.terminated = true;
    }
    
    @Override
    public void run(){
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(video.toURI().toString()));
        Scene scene = new Scene(new Group(new MediaView(mediaPlayer)));
        panel.setScene(scene);
        mediaPlayer.setVolume(0.7);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
