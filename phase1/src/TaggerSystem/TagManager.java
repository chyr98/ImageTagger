package TaggerSystem;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;

public class TagManager implements Serializable{
    private ArrayList<Tag> tags;

    public TagManager(ArrayList<Tag> tags) {
        tags = new ArrayList<Tag>();
    }

    public void AddTag(Tag tag) throws IOException {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        } else {
            throw new IOException("This tag is already in the list");
        }
    }

    public ArrayList<Tag> getTagList() {
        return tags;
    }

    public Boolean HasTag(Tag tag) {
        return this.tags.contains(tag);
    }

    public void DeleteTag(Tag tag) {
        if (HasTag(tag)) {
            this.tags.remove(tag);
        }
    }
}
