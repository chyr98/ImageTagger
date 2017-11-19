package TaggerSystem;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class FileManager implements Serializable {

  //The source folder that user has choose to work on.
  private Folder folder;

  //The path of the source folder.
  private String path;

  public FileManager(Folder folder, String path) {
    this.folder = folder;
    this.path = path;
  }

  public Folder getFolder() {
    return folder;
  }

  public void deleteTag(Tag tag) throws IOException {
    folder.deleteTag(tag);
  }

  public String getPath() {
    return path;
  }

  public ArrayList<ImageFile> getFilesWithTag(Tag tag) {
    return folder.getFileWithTag(tag);
  }

//    public ArrayList<ImageFile> getAllFiles() {
//        FinalList = new ArrayList<ImageFile> ();
//        for (thing : this.folder) {
//            if (thing.isinstanceof(Imagefile)) {
//                FinalList.add(thing);
//            } else {
//                thing.getAllFiles();
//            }
//        }
//        return FinalList;
//    }
}
