package TaggerSystem;

import java.io.File;
import java.nio.file.Path;

public class FileDirectory {

  protected String name;
  protected Folder parent;

  public FileDirectory(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  /**
   * Return the comlete pathName of FileDirectory
   *
   * @return String pathName
   */
  public String pathName() {
    if (this.parent == null) {
      return SystemMain.fileManager.getPath();
    }
    return parent.pathName() + "/" + this.getName();
  }

  /**
   * Return a Path Object refer to this FileDicrctory.
   *
   * @return Path path
   */
  public Path toPath() {
    return (new File(this.pathName())).toPath();
  }

}
