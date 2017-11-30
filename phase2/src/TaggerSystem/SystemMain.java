package TaggerSystem;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This is the class for all executions at the time the program is opened. It has the following
 * responsibilities: 1) Store FileManager. 2) Store TaggerSystem.TagManager. 3) Read the information
 * of files and generate a tree of Files and Folders.
 */
public class SystemMain {

  public static FileManager fileManager;

  public static TagManager tagManager;

  private static FileHandler fileHandler;

  private static Logger nameLog = Logger.getLogger("nameLog.txt");

  private static String[] suffixes = {"jpg", "png", "JPG", "PNG"};

  /**
   * Creates a tree of folders related to the given directory and sub-directories in operating
   * system and initializes a new TagManager and a new FileManager.
   *
   * @param rootPath the pathname string of root directory
   */
  public static void reading(String rootPath) {
    File rootFile = new File(rootPath);
    // Read the information of files and generate a tree of Files and Folders.
    tagManager = new TagManager(new ArrayList<>());
    Folder rootFolder = createFolder(rootFile);
    fileManager = new FileManager(rootFolder, rootPath);
  }

  /**
   * Loads FileManager and TagManager from serialized file restored from previous running of
   * this program.
   */
  public static void loading() {
    // read fileManager from file.
    try {
      FileInputStream fis = new FileInputStream("fileManager.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      fileManager = (FileManager) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    // read tagManager from file.
    try {
      FileInputStream fis = new FileInputStream("tagManager.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);
      tagManager = (TagManager) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Saves FileManager and TagManger for future loading.
   */
  public static void saving() {
    // Save fileManager.
    try {
      // write object to file.
      File fileManagerSerFile = new File("fileManager.ser");
      // a new file will be created iff a file with this name does not yet exist.
      fileManagerSerFile.createNewFile();
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
      File tagManagerSerFile = new File("tagManager.ser");
      // a new file will be created iff a file with this name does not yet exist.
      tagManagerSerFile.createNewFile();
      FileOutputStream fos = new FileOutputStream("tagManager.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(tagManager);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a tree of Folders from given abstract pathname (java.io.File).
   *
   * @param  file       the abstract pathname (java.io.File) of the root directory.
   * @return rootFolder the root of Folder tree.
   */
  private static Folder createFolder(File file) {
    ArrayList<Folder> folderList = new ArrayList<>();
    ArrayList<ImageFile> imageList = new ArrayList<>();
    // Nothing will happen if the directory is empty.
    for (File f : file.listFiles()) {
      // Folder(String name, ArrayList<Folder> children, ArrayList<ImageFile> value)
      if (f.isDirectory()) {
        folderList.add(createFolder(f));
        // So far we only add .jpg and .png files.
      } else if (f.getName().matches(".*\\." + String.join("|.*\\.", suffixes))) {
        String fileName = f.getName();
        ArrayList<Tag> tempTags = new ArrayList<>();
        String[] tagNames = fileName.split(" @");
        if (tagNames.length > 1) {
          for (int i = 1; i < tagNames.length - 1; i++) {
            Tag tempTag = new Tag(tagNames[i].trim());
            tagManager.addTag(tempTag);
            tempTags.add(tempTag);
          }
          String lastTag = tagNames[tagNames.length - 1];
          Tag tempTag = new Tag(lastTag.substring(0, lastTag.length() - 4));
          tagManager.addTag(tempTag);
          tempTags.add(tempTag);
          fileName = tagNames[0].concat(fileName.substring(fileName.length() - 4));
        }
        imageList.add(new ImageFile(fileName, tempTags));
        System.out.println(f.getPath());
      }
    }
    return new Folder(file.getName(), folderList, imageList);
  }

  /**
   * Logs the name change and time stamp to nameLog.txt
   *
   * @param oldName string of the previous name.
   * @param newName string of the new name.
   */
  public static void log(String oldName, String newName) {
    // if fileHandler hasn't been created, create one and add to Logger.
    if (fileHandler == null) {
      try {
        fileHandler = new FileHandler("nameLog.txt", true);
        fileHandler.setFormatter(new SimpleFormatter());
        nameLog.addHandler(fileHandler);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // set message.
    LocalDateTime currTime = LocalDateTime.now();
    String msg = "Old: " + oldName + "; New: " + newName + ";";
    nameLog.info(msg);
  }

}
