package TaggerSystem;

import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import org.w3c.dom.NameList;

public class ImageFile extends FileDirectory implements Serializable {

  /**
   * Stores all the tags this file has ever had. The current tags is stored at the last index of the
   * list.
   */
  private ArrayList<ArrayList<Tag>> tags;

  public ImageFile(String name, ArrayList<Tag> tags) {
    super(name);
    this.tags = new ArrayList<>();
    this.tags.add(tags);
  }

  public ImageFile(String name, ArrayList<Tag> tags, Folder parent) {
    super(name);
    this.tags = new ArrayList<>();
    this.tags.add(tags);
    this.parent = parent;
    parent.getValue().add(this);
  }


  public void addTag(Tag tag) {
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
      }
    }
    renameTo(getName());
  }

  public void copyTo(Folder targetFolder) throws IOException {
    String newName = this.newName(targetFolder);
    ImageFile newImage = new ImageFile(newName, this.getCurrentTagList(), targetFolder);

    Path sourcePath = this.toPath();
    Path targetPath = newImage.toPath();
    Files.copy(sourcePath, targetPath);
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

  public void deleteTag(Tag tag) {
    if ((!this.tags.isEmpty()) && this.tags.get(tags.size() - 1).contains(tag)) {
      String path = getPath();
      ArrayList<Tag> clone = (ArrayList<Tag>) this.tags.get(this.tags.size() - 1).clone();
      clone.remove(tag);
      this.tags.add(clone);
      renameTo(getName());
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

  public boolean hasTag(Tag tag) {
    if (this.getCurrentTagList() == null) {
      return false;
    }
    return this.getCurrentTagList().contains(tag);
  }

  /**
   * Rename this ImageFile to a give String in OS. Each time the renameTo is called, the info will
   * be logged.
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
   * Moves the file to a target folder. If the targetFolder is the current folder where this image
   * lies in, nothing will happen.
   */
  public void moveTo(Folder targetFolder) throws IOException {
    if (targetFolder != this.parent) {
      Path sourcePath = this.toPath();
      this.renameTo(this.newName(targetFolder));
      targetFolder.addImage(this);
      Path targetPath = this.toPath();
      Files.move(sourcePath, targetPath);
    }
  }

  /**
   * Return a new name as described below: if there is no same name in target Folder, then keep name
   * unchanged. if there is a file with the same name, then add (copy) to the start of the filename
   * until there is no such file with same name.
   *
   * @return String newName
   */
  private String newName(Folder targetFolder) {
    HashSet<String> nameSet = targetFolder.hashSet();
    String currName = this.getName();
    while (nameSet.contains(currName)) {
      currName = "(copy)" + currName;
    }
    return currName;
  }


}
