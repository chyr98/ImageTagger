package TaggerSystem;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.lang.annotation.Target;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ImageFile extends FileDirectory implements Serializable {

  /**
   * Stores all the tags this file has ever had. The current tags is stored at the last index of the
   * list.
   */
  private ArrayList<ArrayList<Tag>> tags;

  public ImageFile(String name) {
    super(name);
    this.tags = new ArrayList<>();
    this.tags.add(new ArrayList<>());
  }

  /**
   * Return the path of this image file.
   */
  public String getPath() {
    String ret = this.getName();
    Folder currParent = this.parent;
    while (currParent.getParent() != null) {
      ret = currParent.getName().concat("/" + ret);
      currParent = currParent.getParent();
    }
    return SystemMain.fileManager.getPath().concat("/" + ret);
  }

  /**
   * Return the current name of the file(the name with current attached tags)
   */
  @Override
  public String getName() {
    if (tags.isEmpty()) {
      return name;
    }
    return getNameWithTags(tags.get(tags.size() - 1));
  }


  /**
   * Return the name of a file with given tags attached.
   */
  public String getNameWithTags(ArrayList<Tag> tags) {
    String ret = name.substring(0, name.length() - 4);
    for (Tag tag : tags) {
      ret = ret.concat(" @" + tag.getName());
    }
    ret = ret.concat(name.substring(name.length() - 4));
    return ret;
  }

  public void setParent(Folder folder) {
    this.parent = folder;
  }

  public void addTag(Tag tag) throws IOException {
    String path = getPath();
    if (!SystemMain.tagManager.hasTag(tag)) {
      SystemMain.tagManager.addTag(tag);
    }
    if (this.tags.isEmpty()) {
      ArrayList<Tag> tags = new ArrayList<>();
      tags.add(tag);
      this.tags.add(tags);
    } else {
      if (!this.tags.get(this.tags.size() - 1).contains(tag)) {
        ArrayList<Tag> clone = (ArrayList<Tag>) this.tags.get(this.tags.size() - 1).clone();
        clone.add(tag);
        this.tags.add(clone);
      } else {
        throw new IOException("The file already has this tag");
      }
    }
    renameTo(getName(), path);
  }

  public boolean hasTag(Tag tag) {
    if (this.getCurrentTagList() == null) {
      return false;
    }
    return this.getCurrentTagList().contains(tag);
  }

  public void deleteTag(Tag tag) throws IOException {
    if ((!this.tags.isEmpty()) && this.tags.get(tags.size() - 1).contains(tag)) {
      String path = getPath();
      ArrayList<Tag> clone = (ArrayList<Tag>) this.tags.get(this.tags.size() - 1).clone();
      clone.remove(tag);
      this.tags.add(clone);
      renameTo(getName(), path);
    }
  }

  public ArrayList<Tag> getCurrentTagList() {
    //returns the current attached tags of a file.
    if (!tags.isEmpty()) {
      return tags.get(tags.size() - 1);
    }
    return null;
  }

  /**
   * Return a list of all pass sets of the tags this file has ever attached with
   */
  public ArrayList<ArrayList<Tag>> getAllTagLists() {
    return tags;
  }

  /**
   * Rename this ImageFile to a give String in OS. Each time the renameTo is called, the info will
   * be logged.
   */
  public void renameTo(String newName, String path) {
    File curr = new File(path);
    String oldName = curr.getName();
    curr.renameTo(new File(curr.getParentFile(), newName));
    // log this rename step.
    SystemMain.log(oldName, newName);
  }

  /**
   * Moves the file to a target folder.
   */
  public void moveTo(Folder targetFolder) throws IOException {
    if (!targetFolder.getValue().contains(this)) {
      this.parent.getValue().remove(this);
      targetFolder.addImage(this);
      this.parent = targetFolder;

      Path sourcePath = this.toPath();
      Path targetPath = targetFolder.toPath();
      Files.move(sourcePath, targetPath);
    } else {
      throw new IOException("The Folder has file with same name.");
    }
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ImageFile) {
      return ((ImageFile) other).getName().equals(this.getName());
    } else {
      return false;
    }
  }
}
