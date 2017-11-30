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

  /**
   * Creates a new FileDirectory from a name string.
   *
   * @param name
   */
  public FileDirectory(String name) {
    this.name = name;
  }

  /**
   * Returns the name variable in FileDirectory.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the only Parent of this FileDirectory (i.e. Folder or ImageFile)
   *
   * @return parent a Folder object which is the parent of this FileDirectory.
   */
  public Folder getParent() {
    return parent;
  }

  /**
   * Return the complete path String of FileDirectory
   *
   * @return path a String of path
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
   * Returns a File object as an abstract pathname for this FileDirectory.
   *
   * @return file
   */
  public File toFile() {
    return (new File(this.getPath()));
  }

  /**
   * Returns a Path Object refer to this FileDicrctory.
   *
   * @return path
   */
  public Path toPath() {
    return (new File(this.getPath())).toPath();
  }

  /**
   * Returns the String representation of this FileDirectory. Specifically, returns the name
   * of this FileDirectory.
   *
   * @return name name string of this FileFirectory.
   */
  @Override
  public String toString() {
    return this.name;
  }

  /**
   * Sets the parent of this FileDirectory and does nothing more.
   * The parent will not add this FileDirectory to its children or value in this method.
   *
   * @param parent parent Folder assigned to it.
   */
  public void setParent(Folder parent) {
    this.parent = parent;
  }

}
