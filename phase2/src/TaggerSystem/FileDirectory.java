package TaggerSystem;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class FileDirectory implements Serializable {

  protected String name;
  protected Folder parent;

  public FileDirectory(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Folder getParent() {
    return parent;
  }

  /**
   * Return the comlete path String of FileDirectory
   *
   * @return String of path
   */
  public String getPath() {
    if (this.parent == null) {
      return SystemMain.fileManager.getPath();
    }
    return parent.getPath() + File.separator + this.getName();
  }

  /**
   * Returns an HashSet of Strings contains all Filenames and subdirectories in this fileDirectory.
   * This method works like dir and ls in command line.
   *
   * @return HashSet<String>
   */
  public HashSet<String> hashSet() {
    return (new HashSet<>(Arrays.asList(this.toFile().list())));
  }

  /**
   * Rename this FileDirectory to a given String in OS. Each time the renameTo is called and any change
   * is made, the info will be logged.
   */
  public void renameTo(String newName) {
    File curr = this.toFile();
    String oldName = curr.getName();
    curr.renameTo(new File(curr.getParentFile(), newName));
    // log this rename step if any change is made.
    if (oldName != newName) {
      SystemMain.log(oldName, newName);
    }
  }

  /**
   * Returns a File object as an abstract pathname for this FileDirectory.
   *
   * @return File
   */
  public File toFile() {
    return (new File(this.getPath()));
  }

  /**
   * Return a Path Object refer to this FileDicrctory.
   *
   * @return Path path
   */
  public Path toPath() {
    return (new File(this.getPath())).toPath();
  }

  @Override
  public String toString() {
    return this.name;
  }

  public void setParent(Folder parent) {
    this.parent = parent;
  }

}
