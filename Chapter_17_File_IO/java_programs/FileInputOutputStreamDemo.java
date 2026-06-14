import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStreamDemo {
    public static void main(String[] args) {
        String source = "source.bin";
        String target = "target.bin";
        
        // Write raw bytes
        try (FileOutputStream fos = new FileOutputStream(source)) {
            byte[] data = { 72, 101, 108, 108, 111 }; // "Hello"
            fos.write(data);
            fos.write(10); // newline
            fos.write(new byte[] { 87, 111, 114, 108, 100 }); // "World"
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Read raw bytes
        try (FileInputStream fis = new FileInputStream(source)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            System.out.println("Contents of " + source + ":");
            while ((bytesRead = fis.read(buffer)) != -1) {
                System.out.write(buffer, 0, bytesRead);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Copy bytes from source to target
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            System.out.println("Copied to " + target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
