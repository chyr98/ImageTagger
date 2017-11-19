package TaggerSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Folder implements Serializable {
    private Folder parent;
    private ArrayList<Folder> children;
    private ArrayList<ImageFile> value;
    public Folder(ArrayList<Folder> children, ArrayList<ImageFile> value){
        this.children = children;
        this.value = value;
        for (Folder child:this.children){
            child.setParent(this);
        }
    }

    public void setParent(Folder parent){
        this.parent = parent;
    }
}
