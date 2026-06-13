import java.util.*;

interface Observer {
    void update(String news);
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class NewsAgency implements Subject {
    private List<Observer> observers;
    private String latestNews;
    
    public NewsAgency() {
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }
    
    public void setNews(String news) {
        this.latestNews = news;
        notifyObservers();
    }
}

class NewsChannel implements Observer {
    private String name;
    private String news;
    
    public NewsChannel(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String news) {
        this.news = news;
        System.out.println(name + " received: " + news);
    }
}

public class NewsPublisher {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        
        NewsChannel channel1 = new NewsChannel("CNN");
        NewsChannel channel2 = new NewsChannel("BBC");
        
        agency.registerObserver(channel1);
        agency.registerObserver(channel2);
        
        agency.setNews("Breaking: Java 20 released!");
    }
}
