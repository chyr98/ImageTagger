package TaggerSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class FileManager implements Serializable {

    //The source folder that user has choose to work on.
    private Folder folder;

    public FileManager(Folder folder) {
        this.folder = folder;
    }

    public Folder getFolder(){
        return folder;
    }

    public void DeleteTag(Tag tag) {
        for (ImageFile file: this.folder.getValue()) {
            ArrayList tag_list = file.getCurrentTagList();
            if (tag_list.contains(tag)) {
                clone = tag_list;
                clone.remove(tag);
                file.tags.add(clone);
            }
        }
    }

    public ArrayList<ImageFile> getFilesWithTag(Tag tag) {
        FinalList = new ArrayList<ImageFile> ();
        for (ImageFile file : this.folder.value) {
            ArrayList tag_list = file.getCurrentTagList();
            if (tag_list.contains(tag)) {
                FinalList.add(file);
            }
        }
        return FinalList;
    }

    public ArrayList<ImageFile> getAllFiles() {
        FinalList = new ArrayList<ImageFile> ();
        for (thing : this.folder) {
            if (thing.isinstanceof(Imagefile)) {
                FinalList.add(thing);
            } else {
                thing.getAllFiles();
            }
        }
        return FinalList;
    }
}
