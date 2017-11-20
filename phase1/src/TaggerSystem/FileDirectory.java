package TaggerSystem;

import java.io.File;
import java.nio.file.Path;

public class FileDirectory {

  protected String name;
  protected Folder parent;

  public FileDirectory(String name) {
    this.name = name;
  }

  public String pathName() {
    if (this.parent == null) {
      return SystemMain.fileManager.getPath();
    }
    return parent.pathName() + "/" + name;
  }

  public Path toPath() {
    return (new File(this.pathName())).toPath();
  }
}
