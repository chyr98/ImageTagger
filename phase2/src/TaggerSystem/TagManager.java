package TaggerSystem;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;

public class TagManager implements Serializable {

  private ArrayList<Tag> tags;

  public TagManager(ArrayList<Tag> tags) {
    this.tags = tags;
  }

  /**
   * Adds a Tag to the TagList if this Tag is not in the Taglist. Otherwise adds nothing.
   *
   * @param tag A tag we want to add
   */
  public void addTag(Tag tag) {
    if (!this.tags.contains(tag)) {
      this.tags.add(tag);
    }
  }

  /**
   * Returns an arraylist of all the Tags in the Taglist.
   *
   * @return Taglist An arraylist contains all the Tag in Tag list
   */
  public ArrayList<Tag> getTagList() {
    return tags;
  }

  /**
   * Returns true if this Tag is in Taglist, otherwise returns false.
   *
   * @param tag The Tag we want to check whether is in this Taglist
   * @return boolean
   */
  public Boolean hasTag(Tag tag) {
    return this.tags.contains(tag);
  }

  /**
   * Deletes a Tag if this Tag is in Taglist, otherwise does nothing.
   *
   * @param tag The Tag we want to delete
   * @throws IOException Throws if this Tag is not in the Taglist
   */
  public void deleteTag(Tag tag) throws IOException {
    if (hasTag(tag)) {
      this.tags.remove(tag);
    }
    SystemMain.fileManager.deleteTag(tag);
  }
}
