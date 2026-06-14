import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedWriterReaderDemo {
    public static void main(String[] args) {
        String fileName = "buffered_demo.txt";
        
        // Write using BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("First line");
            bw.newLine();
            bw.write("Second line");
            bw.newLine();
            bw.write("Third line");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Read using BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Contents of " + fileName + ":");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
