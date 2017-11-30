package TaggerSystem;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Folder extends FileDirectory implements Serializable {

  private ArrayList<Folder> children;
  private ArrayList<ImageFile> value;

  /**
   * Creates a Folder by its name. Its children and value will be new ArrayLists. Note: the parent
   * can be set by setParent lately.
   *
   * @param name the string of its name.
   */
  public Folder(String name) {
    super(name);
    this.children = new ArrayList<>();
    this.value = new ArrayList<>();
  }

  /**
   * Creates a Folder by its name, its children and its value. Note: the parent can be set by
   * setParent lately.
   *
   * @param name     name of this Folder.
   * @param children children of this Folder as an ArrayList<Folder>.
   * @param value    images of this Folder as an ArrayList<ImageFile>.
   */
  public Folder(String name, ArrayList<Folder> children, ArrayList<ImageFile> value) {
    super(name);
    this.children = children;
    this.value = value;
    for (Folder child : this.children) {
      child.setParent(this);
    }
    for (ImageFile file : this.value) {
      file.setParent(this);
    }
  }

  /**
   * Adds an image to this folder. the parent of the image will be changed to this folder.
   *
   * @param imageFile image to be added.
   */
  public void addImage(ImageFile imageFile) {
    imageFile.getParent().getValue().remove(imageFile);
    imageFile.setParent(this);
    this.value.add(imageFile);
  }

  /**
   * Deletes an tag from all images in this folder and subFolders. If the tag doesn't exist,
   * nothing will happen.
   * Note: images will be renamed for both file in OS and ImageFile in this program.
   *
   * @param  tag the tag to be deleted.
   * @throws IOException
   */
  public void deleteTag(Tag tag) throws IOException {
    for (ImageFile file : this.value) {
      file.deleteTag(tag);
    }
    if (!this.children.isEmpty()) {
      for (Folder folder : this.children) {
        folder.deleteTag(tag);
      }
    }
  }

  /**
   * Returns an ArrayList of ImageFiles that has specific tag in this folder and subFolders.
   *
   * @param  tag works as a searching key.
   * @return ret ArrayList of all ImageFiles has given tag.
   */
  public ArrayList<ImageFile> getFileWithTag(Tag tag) {
    ArrayList<ImageFile> ret = new ArrayList<>();
    for (ImageFile file : this.value) {
      if (file.hasTag(tag)) {
        ret.add(file);
      }
    }
    if (!this.children.isEmpty()) {
      for (Folder folder : this.children) {
        ret.addAll(folder.getFileWithTag(tag));
      }
    }
    return ret;

  }

  /**
   * Returns all the ImageFiles under this Folder and subFolders.
   *
   * @return ret an ArrayList of all ImageFiles inside this Folder and subFolders.
   */
  public ArrayList<ImageFile> getAllImages() {
    ArrayList<ImageFile> ret = new ArrayList<>();
    ret.addAll(this.getValue());
    for (Folder child : this.children) {
      ret.addAll(child.getAllImages());
    }
    return ret;
  }


  /**
   * Returns the children Folders of this folder.
   *
   * @return children ArrayList of all subFolders.
   */
  public ArrayList<Folder> getChildren() {
    return children;
  }

  /**
   * Returns images in this folder. Images in subFolders are not included.
   *
   * @return value ArrayList of all images.
   */
  public ArrayList<ImageFile> getValue() {
    return value;
  }

}
