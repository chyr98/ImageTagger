package TaggerSystem;

import java.io.*;
import java.util.ArrayList;

/**
 * This is the class for all executions at the time the program is opened. It has the following
 * responsibilities: 1) Store FileManager. 2) Store TaggerSystem.TagManager. 3) Read the information of files and
 * generate a tree of Files and Folders.
 */
public class Main {

  public static FileManager fileManager;

  public static TagManager tagManager = new TagManager(new ArrayList<>());

  public static void main(String[] args) {
    // Read or create fileManager.
    try {
      // read fileManager from file.
      FileInputStream fis = new FileInputStream("fileManager.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      fileManager = (FileManager) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Read or create tagManager.
    try {
      // read tagManager from file.
      FileInputStream fis = new FileInputStream("tagManager.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      tagManager = (TagManager) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      // if tagManager is not found, then create one.
      if (tagManager == null) {
        tagManager = new TagManager(new ArrayList<Tag>());
      }
    }

    // Save fileManager.
    try {
      // write object to file.
      FileOutputStream fos = new FileOutputStream("fileManager.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(fileManager);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Save TaggerSystem.TagManager.
    try {
      // write object to file.
      FileOutputStream fos = new FileOutputStream("tagManager.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(tagManager);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // TODO: Read the information of files and generate a tree of Files and Folders.
  }
}
