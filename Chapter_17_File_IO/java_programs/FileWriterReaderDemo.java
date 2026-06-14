import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileWriterReaderDemo {
    public static void main(String[] args) {
        String fileName = "filewriter_demo.txt";
        
        // Write using FileWriter
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Hello, FileWriter!\n");
            writer.write("This is line 2.\n");
            writer.write("ABC");
            writer.write(10); // newline character
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Read using FileReader
        try (FileReader reader = new FileReader(fileName)) {
            int ch;
            System.out.println("Contents of " + fileName + ":");
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
