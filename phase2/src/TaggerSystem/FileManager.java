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

  /**
   * Returns this folder.
   *
   * @return folder
   */
  public Folder getFolder() {
    return folder;
  }

  /**
   * Deletes a Tag for all the ImageFiles contains this Tag in this Folder.
   *
   * @param tag A Tag we want to delete
   * @throws IOException Throws when no ImageFile in this folder has this Tag
   */
  public void deleteTag(Tag tag) throws IOException {
    folder.deleteTag(tag);
  }

  /**
   * Returns the path of this folder.
   *
   * @return path A path of this folder
   */
  public String getPath() {
    return path;
  }

  /**
   * Returns an ArrayList contains all the ImageFiles in this folder that has the Tag we want.
   *
   * @param tag A Tag we want to search in this Folder
   * @return ArrayList of ImageFile An ArrayList contains all the ImageFiles in this folder that has
   * the special Tag.
   */
  public ArrayList<ImageFile> getFilesWithTag(Tag tag) {
    return folder.getFileWithTag(tag);
  }

  /**
   * Returns all the ImageFiles in this folder.
   *
   * @return ArrayList of ImageFile An arrrayList contains all the ImageFiles in this folder
   */
  public ArrayList<ImageFile> getAllFiles() {
    return folder.getAllImages();
  }
}
