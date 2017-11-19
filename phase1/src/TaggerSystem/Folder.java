package TaggerSystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Folder implements Serializable {
    private String name;
    private Folder parent;
    private ArrayList<Folder> children;
    private ArrayList<ImageFile> value;

    public Folder(String name, ArrayList<Folder> children, ArrayList<ImageFile> value){
        this.name = name;
        this.children = children;
        this.value = value;
        for (Folder child:this.children){
            child.setParent(this);
        }
    }

    public String getName(){
        return name;
    }

    public void setParent(Folder parent){
        this.parent = parent;
    }
}
