package TaggerSystem;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public ImageFile(String name) {
        this.name = name;
        this.tags = new ArrayList<>();
    }

    /**
     * Return the original name of this file(the name without any tag).
     * */
    public String getName(){
        return name;
    }

    /**
     * Return the name of a file with given tags attached.
     * */
    public String getNameWithTags(ArrayList<Tag> tags){
        String ret = name;
        for(Tag tag : tags)
            ret = name.concat(" @"+tag.getName());
        return ret;
    }

    public void AddTag(Tag tag) throws IOException {
        if (this.tags.isEmpty()) {
            ArrayList<Tag> tags = new ArrayList<>();
            tags.add(tag);
            this.tags.add(tags);
        }
        else {
            if (!this.tags.get(this.tags.size() - 1).contains(tag)) {
                ArrayList<Tag> clone = this.tags.get(this.tags.size() - 1);
                clone.add(tag);
                this.tags.add(clone);
                RenameFile();
            } else {
                throw new IOException("The file already has this tag");
            }
        }
    }

    public ArrayList<Tag> getCurrentTagList() {
        //returns the current attached tags of a file.
        if (!tags.isEmpty())
            return tags.get(tags.size()-1);
        return null;
    }

    /**
     * Return a list of all pass sets of the tags this file has ever attached with*/
    public ArrayList<ArrayList<Tag>> getAllTagLists(){
        return tags;
    }

    public void RenameFile() {
        String new_name = getNameWithTags(tags.get(tags.size()-1));
        //TODO: use the new_name to rename the file in the OS.
    }
}
