import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "example.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    modifyFile(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to write text to the file
    private static void writeToFile(Scanner scanner) {
        System.out.print("Enter text to write: ");
        String text = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(text);
            writer.newLine();
            System.out.println("Text written successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read the file content
    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\nFile Contents:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to modify a specific text in the file
    private static void modifyFile(Scanner scanner) {
        System.out.print("Enter text to replace: ");
        String oldText = scanner.nextLine();
        System.out.print("Enter new text: ");
        String newText = scanner.nextLine();

        try {
            Path path = Paths.get(FILE_NAME);
            List<String> lines = Files.readAllLines(path);
            List<String> modifiedLines = new ArrayList<>();

            boolean modified = false;
            for (String line : lines) {
                if (line.contains(oldText)) {
                    modifiedLines.add(line.replace(oldText, newText));
                    modified = true;
                } else {
                    modifiedLines.add(line);
                }
            }

            if (modified) {
                Files.write(path, modifiedLines);
                System.out.println("File updated successfully!");
            } else {
                System.out.println("Text not found in file.");
            }

        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
