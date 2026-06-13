interface MediaPlayer {
    void play(String filename);
}

interface AdvancedMediaPlayer {
    void playMp4(String filename);
    void playVlc(String filename);
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String filename) {
        System.out.println("Playing MP4: " + filename);
    }
    
    @Override
    public void playVlc(String filename) {
        // Do nothing
    }
}

class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String filename) {
        // Do nothing
    }
    
    @Override
    public void playVlc(String filename) {
        System.out.println("Playing VLC: " + filename);
    }
}

class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        }
    }
    
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp4")) {
            advancedPlayer.playMp4(filename);
        } else if (filename.endsWith(".vlc")) {
            advancedPlayer.playVlc(filename);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;
    
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp3")) {
            System.out.println("Playing MP3: " + filename);
        } else {
            mediaAdapter = new MediaAdapter(filename.substring(filename.lastIndexOf(".") + 1));
            mediaAdapter.play(filename);
        }
    }
}

public class MediaPlayerDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("song.mp3");
        player.play("movie.mp4");
        player.play("video.vlc");
    }
}
