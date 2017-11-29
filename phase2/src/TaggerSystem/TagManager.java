package TaggerSystem;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;

public class TagManager implements Serializable {

  private ArrayList<Tag> tags;

  public TagManager(ArrayList<Tag> tags) {
    this.tags = tags;
  }

  public void addTag(Tag tag) throws IOException {
    if (!this.tags.contains(tag)) {
      this.tags.add(tag);
    } else {
      throw new IOException("This tag is already in the list");
    }
  }

  public ArrayList<Tag> getTagList() {
    return tags;
  }

  public Boolean hasTag(Tag tag) {
    return this.tags.contains(tag);
  }

  public void deleteTag(Tag tag) throws IOException {
    if (hasTag(tag)) {
      this.tags.remove(tag);
    }
    SystemMain.fileManager.deleteTag(tag);
  }
}
