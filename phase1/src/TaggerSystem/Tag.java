package TaggerSystem;

import java.io.Serializable;

public class Tag implements Serializable {
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean equals(Tag other){
        return other.getName().equals(this.getName());
    }
}
