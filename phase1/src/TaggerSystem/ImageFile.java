package TaggerSystem;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFile implements Serializable{
    private String name;

    /**Stores all the tags this file has ever had.
     * The current tags is stored at the last index of the list.
     * */
    private ArrayList<ArrayList<Tag>> tags;

    public ImageFile(String name, List<Tag> tags) {
        this.name = name;
        this.tags = new ArrayList<>();
    }

    public void AddTag(Tag tag) throws IOException {
        if (!this.tags.get(this.tags.size()-1).contains(tag)) {
            ArrayList<Tag> clone = this.tags.get(this.tags.size()-1);
            clone.add(tag);
            this.tags.add(clone);
        } else {
            throw new IOException("The file already has this tag");
        }
    }

    public List<Tag> getTagList() {
        //returns the current attached tags of a file.
        return tags.get(tags.size()-1);
    }

    public void RenameFile(String new_name) {
        this.name = new_name;
    }
}
