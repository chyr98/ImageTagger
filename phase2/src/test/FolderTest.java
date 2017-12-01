package test;

import static org.junit.Assert.*;

import TaggerSystem.Folder;
import TaggerSystem.ImageFile;

import TaggerSystem.Tag;
import TaggerSystem.TagManager;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class FolderTest {
    @Test
    public void addImage() throws Exception {
        Tag tag = new Tag("Sam");
        ArrayList ar = new ArrayList();
        ar.add(tag);
        TagManager tm = new TagManager(ar);
        tm.addTag(tag);
        ImageFile image = new ImageFile("IMG321", tm.getTagList(), new Folder("aFolder"));
        ArrayList folder = new ArrayList();
        ArrayList children = new ArrayList();
        Folder checking = new Folder("ame", folder, children);
        checking.addImage(image);
        ArrayList allimages = checking.getAllImages();
        if (allimages.contains(image)){
            System.out.println("it contains image");
        }
    }

    @Test
    public void deleteTag() throws Exception {
    }

    @Test
    public void getFileWithTag() throws Exception {
    }

    @Test
    public void getAllImages() throws Exception {
    }

    @Test
    public void getChildren() throws Exception {
    }

    @Test
    public void getValue() throws Exception {
    }

}