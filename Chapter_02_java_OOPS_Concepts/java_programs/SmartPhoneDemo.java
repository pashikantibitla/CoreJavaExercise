interface Camera {
    void takePhoto();
    void recordVideo();
}

interface MusicPlayer {
    void playMusic();
    void stopMusic();
}

interface GPS {
    void navigate(String destination);
}

class SmartPhone implements Camera, MusicPlayer, GPS {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo...");
    }
    
    @Override
    public void recordVideo() {
        System.out.println("Recording video...");
    }
    
    @Override
    public void playMusic() {
        System.out.println("Playing music...");
    }
    
    @Override
    public void stopMusic() {
        System.out.println("Music stopped");
    }
    
    @Override
    public void navigate(String destination) {
        System.out.println("Navigating to: " + destination);
    }
}

public class SmartPhoneDemo {
    public static void main(String[] args) {
        SmartPhone phone = new SmartPhone();
        phone.takePhoto();
        phone.playMusic();
        phone.navigate("New York");
    }
}
