package test;

import TaggerSystem.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TagManagerTest {

    @Test
    public void addTag() throws Exception {
        ArrayList ar = new ArrayList();
        TagManager tm = new TagManager(ar);
        Tag tag = new Tag("Sam");
        tm.addTag(tag);
        assertTrue(tm.getTagList().contains(tag));

    }

    @Test
    public void getTagList() throws Exception {
        ArrayList ar = new ArrayList();
        TagManager tm = new TagManager(ar);
        Tag tag = new Tag("Sam");
        tm.addTag(tag);
        ArrayList actual = new ArrayList();
        actual.add(tag);
        assertEquals(tm.getTagList(), actual);
    }

    @Test
    public void hasTag() throws Exception {
        ArrayList ar = new ArrayList();
        TagManager tm = new TagManager(ar);
        Tag tag = new Tag("Sam");
        tm.addTag(tag);
        assertTrue(tm.hasTag(tag));
    }

    @Test
    public void deleteTag() throws Exception {
        ArrayList ar = new ArrayList();
        TagManager tm = new TagManager(ar);
        Tag tag = new Tag("Sam");
        tm.addTag(tag);
        SystemMain.fileManager=new FileManager(new Folder("A Folder"), "");
        tm.deleteTag(tag);
        assertEquals(tm.getTagList().size(), 0);
    }

}