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

  private static String[] suffixes = {"jpg", "png"};


  public SystemMain(String rootPath) {

    File rootFile = new File(rootPath);

    // Read or create Managers.
    try {
      // read fileManager from file.
      FileInputStream fisF = new FileInputStream(rootPath + "/fileManager.ser");
      ObjectInputStream oisF = new ObjectInputStream(fisF);
      fileManager = (FileManager) oisF.readObject();

      // read tagManager from file.
      FileInputStream fisT = new FileInputStream(rootPath + "/tagManager.ser");
      ObjectInputStream oisT = new ObjectInputStream(fisT);
      tagManager = (TagManager) oisT.readObject();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    // Save Managers.
    try {
      // write fileManager to file.
      File fileManagerSerFile = new File(rootFile, "fileManager.ser");
      // a new file will be created iff a file with this name does not yet exist.
      fileManagerSerFile.createNewFile();
      FileOutputStream fosF = new FileOutputStream(rootPath + "/fileManager.ser");
      ObjectOutputStream oosF = new ObjectOutputStream(fosF);
      oosF.writeObject(fileManager);
      oosF.close();

      // write tagManager to file.
      File tagManagerSerFile = new File(rootFile, "tagManager.ser");
      // a new file will be created iff a file with this name does not yet exist.
      tagManagerSerFile.createNewFile();
      FileOutputStream fosT = new FileOutputStream(rootPath + "/tagManager.ser");
      ObjectOutputStream oosT = new ObjectOutputStream(fosT);
      oosT.writeObject(tagManager);
      oosT.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    // Read the information of files and generate a tree of Files and Folders.
    Folder rootFolder = createFolder(rootFile);
  }


  private Folder createFolder(File file) {
    ArrayList<Folder> folderList = new ArrayList<>();
    ArrayList<ImageFile> imageList = new ArrayList<>();
    // Nothing will happen if the directory is empty.
    for (File f : file.listFiles()) {
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
