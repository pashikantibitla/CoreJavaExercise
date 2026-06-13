abstract class Game {
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
    
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
}

class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket game initialized");
    }
    
    @Override
    void startPlay() {
        System.out.println("Cricket game started");
    }
    
    @Override
    void endPlay() {
        System.out.println("Cricket game ended");
    }
}

class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football game initialized");
    }
    
    @Override
    void startPlay() {
        System.out.println("Football game started");
    }
    
    @Override
    void endPlay() {
        System.out.println("Football game ended");
    }
}

public class GameTemplate {
    public static void main(String[] args) {
        Game cricket = new Cricket();
        cricket.play();
        
        System.out.println();
        
        Game football = new Football();
        football.play();
    }
}
