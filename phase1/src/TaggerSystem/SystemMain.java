package TaggerSystem;

import java.io.*;
import java.util.ArrayList;

/**
 * This is the class for all executions at the time the program is opened. It has the following
 * responsibilities: 1) Store FileManager. 2) Store TaggerSystem.TagManager. 3) Read the information
 * of files and generate a tree of Files and Folders.
 */
public class SystemMain {

  private static FileManager fileManager;

  private static TagManager tagManager;

  private static String[] suffixes = {".jpg", ".png"};

  public SystemMain(String rootPath) {

    File rootFile = new File(rootPath);

    // Read or create fileManager.
    try {
      // read fileManager from file.
      FileInputStream fis = new FileInputStream(rootPath + "/fileManager.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      fileManager = (FileManager) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Read or create tagManager.
    try {
      // read tagManager from file.
      FileInputStream fis = new FileInputStream(rootPath + "/tagManager.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      tagManager = (TagManager) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Save fileManager.
    try {
      // write object to file.
      File fileManagerSerFile = new File(rootFile, "fileManager.ser");
      // a new file will be created iff a file with this name does not yet exist.
      fileManagerSerFile.createNewFile();
      FileOutputStream fos = new FileOutputStream(rootPath + "/fileManager.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(fileManager);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Save TaggerSystem.TagManager.
    try {
      // write object to file.
      File tagManagerSerFile = new File(rootFile, "tagManager.ser");
      // a new file will be created iff a file with this name does not yet exist.
      tagManagerSerFile.createNewFile();
      FileOutputStream fos = new FileOutputStream(rootPath + "/tagManager.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(tagManager);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Read the information of files and generate a tree of Files and Folders.
    Folder rootFolder = createFolder(rootFile);
  }

  private Folder createFolder(File file) {
    File[] files = file.listFiles();
    ArrayList<Folder> folderList = new ArrayList<>();
    ArrayList<ImageFile> imageList = new ArrayList<>();
    for (File f : files) {
      // Folder(String name, ArrayList<Folder> children, ArrayList<ImageFile> value)
      if (f.isDirectory()) {
        folderList.add(createFolder(f));
        // So far we only add .jpg and .png files.
      } else if (f.getName().matches(".*\\." + String.join("|.*\\.", suffixes))) {
        imageList.add(new ImageFile(f.getName()));
      }
    }
    return new Folder(file.getName(), folderList, imageList);
  }

}
