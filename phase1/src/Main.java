import java.io.FileOutputStream;

/**
 * This is the class for all executions at the time the program is opened.
 * It has the following responsibilities:
 * 1) Store FileManager.
 * 2) Store TagManager.
 * 3) Read the information of files and generate a tree of Files and Folders.
 */
public class Main {
    public static void main(String[] args) {
        // TODO: Store FileManager.
        FileManager fileManager = new FileManager();
        try {
            FileOutputStream outputStream = new FileOutputStream("fileManager.ser");
        } catch (java.io.FileNotFoundException fnf) {

        }

        // TODO: Store TagManager.
        // TODO: Read the information of files and generate a tree of Files and Folders.
    }
}
