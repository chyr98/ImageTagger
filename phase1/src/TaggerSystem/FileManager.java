package TaggerSystem;

import java.io.Serializable;

public class FileManager implements Serializable {

    //The folder indicates where is the user working at on GUI.
    private Folder currentFolder;

    public Folder getCurrentFolder() {
        return currentFolder;
    }
}
