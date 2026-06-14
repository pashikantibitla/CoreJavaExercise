import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class NIO2PathFilesDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("nio2_demo.txt");
        
        // Create file if not exists
        if (!Files.exists(path)) {
            Files.createFile(path);
            System.out.println("Created: " + path);
        }
        
        // Write lines
        List<String> lines = List.of("First line", "Second line", "Third line");
        Files.write(path, lines);
        System.out.println("Wrote lines to " + path);
        
        // Read all lines
        List<String> readLines = Files.readAllLines(path);
        System.out.println("Contents:");
        readLines.forEach(System.out::println);
        
        // Copy file
        Path copy = Paths.get("nio2_demo_copy.txt");
        Files.copy(path, copy, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Copied to " + copy);
        
        // Move file
        Path moved = Paths.get("nio2_demo_moved.txt");
        Files.move(copy, moved, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Moved to " + moved);
        
        // Delete moved file
        Files.deleteIfExists(moved);
        System.out.println("Deleted " + moved);
        
        // File attributes
        System.out.println("Size: " + Files.size(path));
        System.out.println("Readable: " + Files.isReadable(path));
        System.out.println("Writable: " + Files.isWritable(path));
        
        // Walk directory tree
        Path root = Paths.get(".");
        System.out.println("\nDirectory tree (max 2 levels):");
        Files.walk(root, 2).forEach(System.out::println);
        
        // Cleanup
        Files.deleteIfExists(path);
    }
}
