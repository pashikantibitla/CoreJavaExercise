import java.io.File;
import java.io.IOException;

public class FileOperations {
    public static void main(String[] args) throws IOException {
        File file = new File("test_file.txt");
        
        // Create file if it does not exist
        if (!file.exists()) {
            boolean created = file.createNewFile();
            System.out.println("File created: " + created);
        }
        
        // Check properties
        System.out.println("Exists: " + file.exists());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Is Directory: " + file.isDirectory());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Name: " + file.getName());
        System.out.println("Parent: " + file.getParent());
        System.out.println("Length: " + file.length());
        System.out.println("Can Read: " + file.canRead());
        System.out.println("Can Write: " + file.canWrite());
        
        // Rename
        File renamed = new File("renamed_file.txt");
        boolean renamedSuccess = file.renameTo(renamed);
        System.out.println("Renamed: " + renamedSuccess);
        
        // Delete
        boolean deleted = renamed.delete();
        System.out.println("Deleted: " + deleted);
        
        // Directory operations
        File dir = new File("test_dir");
        if (!dir.exists()) {
            dir.mkdir();
        }
        System.out.println("Is Directory: " + dir.isDirectory());
        
        File nested = new File("test_dir/subdir");
        nested.mkdirs();
        System.out.println("Nested dirs created: " + nested.exists());
        
        // Cleanup
        nested.delete();
        dir.delete();
    }
}
