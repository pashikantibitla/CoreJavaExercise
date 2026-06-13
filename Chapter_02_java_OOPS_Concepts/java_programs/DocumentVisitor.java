import java.util.*;

interface Visitor {
    void visit(TextDocument document);
    void visit(ImageDocument document);
}

interface Document {
    void accept(Visitor visitor);
}

class TextDocument implements Document {
    private String content;
    
    public TextDocument(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class ImageDocument implements Document {
    private String filename;
    private int width;
    private int height;
    
    public ImageDocument(String filename, int width, int height) {
        this.filename = filename;
        this.width = width;
        this.height = height;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public int getSize() {
        return width * height;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class WordCountVisitor implements Visitor {
    private int wordCount = 0;
    
    @Override
    public void visit(TextDocument document) {
        wordCount += document.getContent().split("\\s+").length;
    }
    
    @Override
    public void visit(ImageDocument document) {
        // Images don't have words
    }
    
    public int getWordCount() {
        return wordCount;
    }
}

class RenderVisitor implements Visitor {
    @Override
    public void visit(TextDocument document) {
        System.out.println("Rendering text: " + document.getContent());
    }
    
    @Override
    public void visit(ImageDocument document) {
        System.out.println("Rendering image: " + document.getFilename());
    }
}

public class DocumentVisitor {
    public static void main(String[] args) {
        Document[] documents = {
            new TextDocument("Hello world this is a test"),
            new ImageDocument("photo.jpg", 1920, 1080),
            new TextDocument("Another document with more words")
        };
        
        WordCountVisitor wordCounter = new WordCountVisitor();
        RenderVisitor renderer = new RenderVisitor();
        
        for (Document doc : documents) {
            doc.accept(wordCounter);
            doc.accept(renderer);
        }
        
        System.out.println("Total words: " + wordCounter.getWordCount());
    }
}
