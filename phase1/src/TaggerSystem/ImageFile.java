package TaggerSystem;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;

public class ImageFile implements Serializable{
    private String name;

    private Folder parent;

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
     * Return the current name of the file(the name with current attached tags)*/
    public String getCurrName(){
        if (tags.isEmpty())
            return name;
        return getNameWithTags(tags.get(tags.size()-1));
    }

    /**
     * Return the path of this image file.*/
    public String getPath(){
        String ret = this.name;
        Folder currParent = this.parent;
        while (currParent.getParent()!=null){
            ret = currParent.getName().concat("/"+ret);
            currParent=currParent.getParent();
        }
        return SystemMain.fileManager.getPath().concat("/"+ret);
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

    public void setParent(Folder folder){
        this.parent = folder;
    }

    public void AddTag(Tag tag) throws IOException {
        if (!SystemMain.tagManager.HasTag(tag))
            SystemMain.tagManager.AddTag(tag);
        if (this.tags.isEmpty()) {
            ArrayList<Tag> tags = new ArrayList<>();
            tags.add(tag);
            this.tags.add(tags);
        }
        else {
            if (!this.tags.get(this.tags.size() - 1).contains(tag)) {
                ArrayList<Tag> clone = (ArrayList<Tag>) this.tags.get(this.tags.size() - 1).clone();
                clone.add(tag);
                this.tags.add(clone);
                RenameFile();
            } else {
                throw new IOException("The file already has this tag");
            }
        }
    }

    public boolean hasTag(Tag tag){
        if(this.getCurrentTagList()==null)
            return false;
        return this.getCurrentTagList().contains(tag);
    }

    public void deleteTag(Tag tag)throws IOException{
        ArrayList<Tag> clone = this.tags.get(this.tags.size() - 1);
        clone.remove(tag);
        this.tags.add(clone);
        RenameFile();
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
