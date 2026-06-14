import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileIOInterviewProblem {
    public static void main(String[] args) throws IOException {
        String fileName = "word_count.txt";
        
        // Create sample file
        List<String> content = Arrays.asList(
            "Java is great",
            "Java is platform independent",
            "Java is object oriented",
            "File IO is important in Java"
        );
        Files.write(Paths.get(fileName), content);
        
        // Problem: Count word frequency in the file
        Map<String, Integer> wordCount = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\s+");
                for (String word : words) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }
        
        System.out.println("Word Frequency:");
        wordCount.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        
        // Cleanup
        Files.deleteIfExists(Paths.get(fileName));
    }
}
