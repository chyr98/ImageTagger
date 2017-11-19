package TaggerSystem;

import java.io.IOException;
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
        for (ImageFile file:this.value){
            file.setParent(this);
        }
    }

    public void deleteTag(Tag tag) throws IOException {
        for(ImageFile file : this.value)
            file.deleteTag(tag);
        if(!this.children.isEmpty()){
            for(Folder folder:this.children)
                folder.deleteTag(tag);
        }
    }



    public ArrayList<ImageFile> getFileWithTag(Tag tag){
        ArrayList<ImageFile> ret = new ArrayList<>();
        for (ImageFile file:this.value){
            if(file.hasTag(tag))
                ret.add(file);
        }
        if(!this.children.isEmpty()){
            for(Folder folder:this.children)
                ret.addAll(folder.getFileWithTag(tag));
        }
        return ret;

    }

    public String getName(){
        return name;
    }

    public ArrayList<Folder> getChildren() {
        return children;
    }

    public ArrayList<ImageFile> getValue() {
        return value;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent){
        this.parent = parent;
    }
}
