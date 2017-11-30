package TaggerSystem;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;

public class FileDirectory implements Serializable {

  protected String name;
  protected Folder parent;

  public FileDirectory(String name) {
    this.name = name;
  }

  public FileDirectory(String name, Folder parent) {
    this.name = name;
    this.parent = parent;
  }

  public String getName() {
    return name;
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
   * Return a Path Object refer to this FileDicrctory.
   *
   * @return Path path
   */
  public Path toPath() {
    return (new File(this.getPath())).toPath();
  }

}
