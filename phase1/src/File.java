<<<<<<< HEAD
import java.io.Serializable;

public class File implements Serializable {
=======
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File {
    private String name;
    private List<Tag> tags;

    public File(String name, List<Tag> tags) {
        this.name = name;
        this.tags = new ArrayList<>();
    }

    public void AddTag(Tag tag) throws IOException {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        } else {
            throw new IOException("The file already has this tag");
        }
    }

    public List<Tag> getTagList() {
        return tags;
    }

    public void RenameFile(String new_name) {
        this.name = new_name;
    }
>>>>>>> b9b1defd9f593f8c12f762b6afd400c236a6a22f
}
