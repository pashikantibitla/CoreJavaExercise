import java.io.PrintWriter;
import java.io.IOException;

public class PrintWriterDemo {
    public static void main(String[] args) {
        String fileName = "printwriter_demo.txt";
        
        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.println("Hello, PrintWriter!");
            pw.print("Value: ");
            pw.println(42);
            pw.printf("Pi = %.5f%n", Math.PI);
            pw.write("Raw text line\n");
            
            if (pw.checkError()) {
                System.err.println("Error occurred while writing");
            }
        }
        
        System.out.println("Wrote to " + fileName);
    }
}
